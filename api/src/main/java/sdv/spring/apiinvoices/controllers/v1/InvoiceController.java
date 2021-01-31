package sdv.spring.apiinvoices.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdv.spring.apiinvoices.domain.CurrencyCBRFNode;
import sdv.spring.apiinvoices.domain.Invoice;
import sdv.spring.apiinvoices.exception.InvoiceIsAlreadyReversedException;
import sdv.spring.apiinvoices.exception.InvoiceNotFoundException;
import sdv.spring.apiinvoices.mapper.InvoiceMapper;
import sdv.spring.apiinvoices.model.InvoiceDTO;
import sdv.spring.apiinvoices.model.ReversionDTO;
import sdv.spring.apiinvoices.service.CBRFService;
import sdv.spring.apiinvoices.services.InvoiceService;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceMapper invoiceMapper = InvoiceMapper.INSTANCE;

    private final CBRFService cbrfService;

    public InvoiceController(InvoiceService invoiceService, CBRFService cbrfService) {
        this.invoiceService = invoiceService;
        this.cbrfService = cbrfService;
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE , headers = "content-type=application/xml")
    public ResponseEntity<Object> getAllInvoicesXML(){
        return new ResponseEntity<>(invoiceService.getAllInvoicesDTO(), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE , headers = "content-type=application/json")
    public ResponseEntity<Object> getAllInvoicesJson(){
        return new ResponseEntity<>(invoiceService.getAllInvoicesDTO(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{invNumber}"}, produces = MediaType.APPLICATION_XML_VALUE, headers="content-type=application/xml")
    public ResponseEntity<Object> getInvoiceByNumberXML(@PathVariable String invNumber){
        return new ResponseEntity<>(invoiceService.getInvoiceDTOByNumber(invNumber), HttpStatus.OK);
    }

    @GetMapping(path = {"/{invNumber}"}, produces = MediaType.APPLICATION_JSON_VALUE, headers="content-type=application/json")
    public ResponseEntity<Object> getInvoiceByNumberJson(@PathVariable String invNumber){
        return new ResponseEntity<>(invoiceService.getInvoiceDTOByNumber(invNumber), HttpStatus.OK);
    }

    @PostMapping(consumes =  MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE,
            headers="content-type=application/xml")
    public ResponseEntity<Object> postInvoiceXML(@RequestBody InvoiceDTO invoiceDTO){

        CalculateAmountInRuble(invoiceDTO);
        return new ResponseEntity<>(invoiceService.postInvoiceDTO(invoiceDTO),HttpStatus.CREATED);
    }

    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE,
            headers="content-type=application/json")
    public ResponseEntity<Object> postInvoiceJson(@RequestBody InvoiceDTO invoiceDTO){
        CalculateAmountInRuble(invoiceDTO);
        return new ResponseEntity<>(invoiceService.postInvoiceDTO(invoiceDTO),HttpStatus.CREATED);
    }

    @PutMapping(
            consumes =  MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE,
            headers="content-type=application/xml")
    public ResponseEntity<Object> putInvoiceXML(@RequestBody InvoiceDTO invoiceDTO){
        CalculateAmountInRuble(invoiceDTO);
        return new ResponseEntity<>(invoiceService.putInvoiceDTO(invoiceDTO),HttpStatus.CREATED);
    }

    @PutMapping(
            consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE,
            headers="content-type=application/json")
    public ResponseEntity<Object> putInvoiceJson(@RequestBody InvoiceDTO invoiceDTO){
        CalculateAmountInRuble(invoiceDTO);
        return new ResponseEntity<>(invoiceService.putInvoiceDTO(invoiceDTO),HttpStatus.CREATED);
    }

    private void CalculateAmountInRuble(InvoiceDTO invoiceDTO){
        invoiceDTO.getInvoicelines().forEach(item->{
            if (!item.getCurr().getCurrencyCode().equals("RUB")){
                List<CurrencyCBRFNode> currCBRF=  cbrfService.getCurrencyRateOnTheDate(
                        item.getCurr(),
                        Date.from(invoiceDTO.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant())
                );
                BigDecimal rate = currCBRF.get(0).getValue();
                Integer nominal = currCBRF.get(0).getNominal();
                BigDecimal amountInRubles = item.getBaseamount().multiply(rate).divide(BigDecimal.valueOf(nominal));
                item.setAmountInRubles( amountInRubles );
            }
        });
    }

    @PutMapping( path = "/reverse",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            headers="content-type=application/json")
    public ResponseEntity<Object> reverseInvoiceJSON(@RequestBody ReversionDTO reversionDTO){
        return makeInvoiceReversal(reversionDTO);
    }

    @PutMapping( path = "/reverse",
            consumes =  MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE,
            headers="content-type=application/xml")
    public ResponseEntity<Object> reverseInvoiceXML(@RequestBody ReversionDTO reversionDTO){
        return makeInvoiceReversal(reversionDTO);
    }

    private ResponseEntity<Object> makeInvoiceReversal(ReversionDTO reversionDTO){
        Invoice invoiceForReversion = invoiceService.findByNumberAndCompanyIssuerAndIsReversed(
                reversionDTO.getInvoiceNumber(), reversionDTO.getCompanyissuer(),
        Boolean.valueOf("FALSE"));
        if (invoiceForReversion == null){
            throw new InvoiceNotFoundException("Invoice does not exist");
        }
        if (invoiceForReversion.getIsReversed()){
            throw new InvoiceIsAlreadyReversedException("Provided Invoice is already reversed");
        }
        invoiceForReversion.setIsReversed(true);
        return new ResponseEntity<>(
                invoiceMapper.invoiceToInvoiceDTO(invoiceService.save(invoiceForReversion)),
                HttpStatus.OK);
    }
}
