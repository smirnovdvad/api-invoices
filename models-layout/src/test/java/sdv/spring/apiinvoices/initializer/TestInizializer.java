package sdv.spring.apiinvoices.initializer;

import sdv.spring.apiinvoices.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

public abstract class TestInizializer {

    public Company companyIssuer;
    public Company companyReceiver;
    public PaymentMean paymentMean1;
    public PaymentMean paymentMean2;
    public InvoiceLine invoiceLine;
    public Good good;
    public UnitOfMeasure unitOfMeasure;
    public Invoice invoice;
    public Currency usd;
    public Set<InvoiceLine> invLines = new HashSet<>();
    public Set<PaymentMean> pm = new HashSet<>();
    public Set<Invoice> invoices = new HashSet<>();

    public String invoiceNumber = "Inv1";
    public Long invoiceId = 55L;
    public LocalDate invoiceDate = LocalDate.now();

    public void completeInit(){
        this.initCompanyIssuer();

        this.initCompanyReceiver();

        this.initUnitOfMeasure();

        this.initPaymentMean();

        this.initCurrency();

        this.initGood();

        this.initInvoiceLine();

        this.initInvoice();
    }

    public void initCompanyIssuer(){
        companyIssuer = Company.builder()
                .id(1L)
                .address("Company Issuer Address")
                .country("Russia")
                .name("Company Issuer")
                .email("compissuer@mail.ru")
                .tin("12345678")
                .phone("1234567")
                .build();
    }

    public void initCompanyReceiver(){
        companyReceiver = Company.builder()
                .id(2L)
                .address("Company Receiver Address")
                .country("Belarus")
                .name("Company Receiver")
                .email("companyreceiver@mail.ru")
                .tin("876543321")
                .phone("7654321")
                .build();
    }

    public void initUnitOfMeasure(){
        UnitOfMeasure unitOfMeasure = UnitOfMeasure.builder()
                .name("KG")
                .description("Kilos").build();
    }

    public void initPaymentMean(){
        PaymentMean paymentMean1 = PaymentMean.builder()
                .description("Avans 10 %").build();

        PaymentMean paymentMean2 = PaymentMean.builder()
                .description("not later than 30 days").build();

        pm.add(paymentMean1);
        pm.add(paymentMean2);
    }

    public void initCurrency(){
        usd = Currency.getInstance("USD");
    }

    public void initGood(){
        good = Good.builder()
                .name("Patotes")
                .uom(unitOfMeasure)
                .build();
    }

    public void initInvoiceLine(){
        invoiceLine = InvoiceLine.builder()
                .id(33L)
                .good(good)
                .linenumber("0100")
                .quantity(new BigDecimal(1.00))
                .baseamount(new BigDecimal(100))
                .curr(usd)
                .build();

        invLines.add(invoiceLine);
    }

    public void initInvoice(){
        invoice = Invoice.builder()
                .date(this.invoiceDate)
                .companyissuer(companyIssuer)
                .companyreceiver(companyReceiver)
                .invoicelines(invLines)
                .paymentmeans(pm)
                .number(invoiceNumber)
                .id(invoiceId)
                .build();

        invoices.add(invoice);
    }

}
