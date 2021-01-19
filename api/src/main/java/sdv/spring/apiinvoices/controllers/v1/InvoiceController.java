package sdv.spring.apiinvoices.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdv.spring.apiinvoices.model.InvoiceDTO;
import sdv.spring.apiinvoices.services.InvoiceService;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
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
        return new ResponseEntity<>(invoiceService.postInvoiceDTO(invoiceDTO),HttpStatus.CREATED);
    }

    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE,
            headers="content-type=application/json")
    public ResponseEntity<Object> postInvoiceJson(@RequestBody InvoiceDTO invoiceDTO){
        return new ResponseEntity<>(invoiceService.postInvoiceDTO(invoiceDTO),HttpStatus.CREATED);
    }

    @PutMapping(
            consumes =  MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE,
            headers="content-type=application/xml")
    public ResponseEntity<Object> putInvoiceXML(@RequestBody InvoiceDTO invoiceDTO){
        return new ResponseEntity<>(invoiceService.putInvoiceDTO(invoiceDTO),HttpStatus.CREATED);
    }

    @PutMapping(
            consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE,
            headers="content-type=application/json")
    public ResponseEntity<Object> putInvoiceJson(@RequestBody InvoiceDTO invoiceDTO){
        return new ResponseEntity<>(invoiceService.putInvoiceDTO(invoiceDTO),HttpStatus.CREATED);
    }
}
