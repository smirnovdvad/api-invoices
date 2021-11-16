package sdv.spring.apiinvoices.services.springdatajpa;

import lombok.extern.slf4j.Slf4j;
import sdv.spring.apiinvoices.domain.*;
import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.exception.InvoiceDuplicateNumber;
import sdv.spring.apiinvoices.exception.InvoiceLineNotFoundException;
import sdv.spring.apiinvoices.exception.InvoiceNotFoundException;
import sdv.spring.apiinvoices.mapper.CompanyMapper;
import sdv.spring.apiinvoices.mapper.InvoiceMapper;
import sdv.spring.apiinvoices.model.CompanyDTO;
import sdv.spring.apiinvoices.model.InvoiceDTO;
import sdv.spring.apiinvoices.repository.InvoiceRepository;
import sdv.spring.apiinvoices.services.*;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class InvoiceJPAService extends  CrudServiceCommon<Invoice,Long,InvoiceRepository>
                               implements  InvoiceService{

    private InvoiceMapper invoiceMapper = InvoiceMapper.INSTANCE;
    private CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    public InvoiceJPAService(InvoiceRepository invoiceRepository, CompanyService aCompanyService, InvoiceLineService aInvoiceLineService,
                             PaymentMeanService aPaymentMeanService, GoodService aGoodService) {
        super(invoiceRepository);
        this.companyService = aCompanyService;
        this.invoiceLineService = aInvoiceLineService;
        this.paymentMeanService = aPaymentMeanService;
        this.goodService = aGoodService;
    }
    private final CompanyService companyService;
    private final InvoiceLineService invoiceLineService;
    private final PaymentMeanService paymentMeanService;
    private final GoodService goodService;

    @Override
    @Transactional
    public Invoice save(Invoice object) {
        Company companytmp;
        companytmp = companyService.findByTin(object.getCompanyissuer().getTin());
        if (companytmp != null)
            object.getCompanyissuer().setId(companytmp.getId());
        else
            companyService.save(object.getCompanyissuer());

        companytmp = companyService.findByTin(object.getCompanyreceiver().getTin());
        if (companytmp != null)
            object.getCompanyreceiver().setId(companytmp.getId());
        else
            companyService.save(object.getCompanyreceiver());

        object.getPaymentmeans().forEach(paymentMean -> {
            PaymentMean paymentMeanTmp;
            paymentMeanTmp = paymentMeanService.findByDescription(paymentMean.getDescription());
            if ( paymentMeanTmp != null)
            {
                paymentMean.setId(paymentMeanTmp.getId());
                paymentMean.getInvoices().add(object);
            }
            else
                paymentMeanService.save(paymentMean);
        });

        object.getInvoicelines().forEach(invoiceLine -> {
            InvoiceLine savedInvoiceLine = null;
            invoiceLine.getGood().getInvoiceLines().add(invoiceLine);
            Good good = goodService.findByName(invoiceLine.getGood().getName());

            if (good != null)
                invoiceLine.getGood().setId(good.getId());
            else
                goodService.save(invoiceLine.getGood());
            invoiceLine.setInvoice(object);
            if (object.getId() != null)
            {
                log.info(object.getId().toString());
                savedInvoiceLine =
                        invoiceLineService.findByLineNumberAndInvoice(invoiceLine.getLinenumber(),object);
                if (savedInvoiceLine == null)
                    throw new InvoiceLineNotFoundException("Invoice Line with number "+ invoiceLine.getLinenumber() +
                            " can not be updated due to not existence");
            }
            if (savedInvoiceLine!=null)
                invoiceLine.setId(savedInvoiceLine.getId());
        });
        return this.getRepository().save(object);
    }

    @Override
    public InvoiceDTO postInvoiceDTO(InvoiceDTO invoiceDTO) {
        Optional<Invoice> optInvoice = this.getRepository().findByNumberAndIsReversed(
                invoiceDTO.getNumber(),
                Boolean.valueOf("FALSE") );

        if (optInvoice.isPresent() &&
                invoiceMapper.invoiceDtoToInvoice(invoiceDTO).getCompanyissuer()
                        .equals(optInvoice.get().getCompanyissuer()) &&
            optInvoice.get().getIsReversed() == false)
        {
            log.error("Invoice already exist + " + invoiceDTO.toString());
            throw new InvoiceDuplicateNumber("This Invoice already exists. Before posting new document you need " +
                    "to reverse old one");

        }

        return invoiceMapper.invoiceToInvoiceDTO(save(
                invoiceMapper.invoiceDtoToInvoice(invoiceDTO)
        ));
    }

    @Override
    public Set<InvoiceDTO> getAllInvoicesDTO() {
        HashSet<InvoiceDTO> resInvoicesDTO = new HashSet<>();
        findAll().forEach(
                invoice -> {
                    resInvoicesDTO.add(invoiceMapper.invoiceToInvoiceDTO(invoice));
                }
        );
        return resInvoicesDTO;
    }

    @Override
    public InvoiceDTO putInvoiceDTO(InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceMapper.invoiceDtoToInvoice(invoiceDTO);
        Optional<Invoice> optInvoice = this.getRepository().findByNumberAndIsReversed(invoice.getNumber(),
                Boolean.valueOf("FALSE"));
        if (optInvoice.isPresent())
        {
            invoice.setId(optInvoice.get().getId());
        }

        return invoiceMapper.invoiceToInvoiceDTO(save(invoice));
    }

    @Override
    public InvoiceDTO getInvoiceDTOByNumber(String aInvNumber) {
        Optional<Invoice> optInvoice = this.getRepository().findByNumber(aInvNumber);
        if (optInvoice.isPresent())
            return invoiceMapper.invoiceToInvoiceDTO(optInvoice.get());
        else{
            log.error("Invoice with number " + aInvNumber + " does not exist");
            throw new InvoiceNotFoundException("Invoice does not exist");
        }
    }

    @Override
    public Invoice findByNumber(String aInvNumber) {
        Optional<Invoice> optInvoice = this.getRepository().findByNumber(aInvNumber);
        if (optInvoice.isPresent())
            return optInvoice.get();
        else
            throw new InvoiceNotFoundException("Invoice does not exist");
    }

    @Override
    public Invoice findByNumberAndCompanyIssuerAndIsReversed(String aInvNumber, CompanyDTO companyIssuer, Boolean isReversed) {
        Optional<Invoice> optInvoice = this.getRepository().findByNumberAndCompanyIssuerAndIsReversed(
                aInvNumber, companyMapper.companyDtoToCompany(companyIssuer), isReversed
        );
        if (optInvoice.isPresent())
            return optInvoice.get();
        else
            return null;
    }

    @Override
    public Invoice findByNumberAndIsReversed(String aInvNumber, Boolean isReversed) {
        Optional<Invoice> optionalInvoice = this.getRepository().findByNumberAndIsReversed(aInvNumber,Boolean.valueOf("FALSE"));
        if (optionalInvoice.isPresent())
            return optionalInvoice.get();
        else
            return null;
    }
}
