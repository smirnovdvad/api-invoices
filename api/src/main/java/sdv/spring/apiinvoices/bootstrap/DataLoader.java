package sdv.spring.apiinvoices.bootstrap;

import sdv.spring.apiinvoices.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sdv.spring.apiinvoices.services.InvoiceService;
import sdv.spring.apiinvoices.services.PaymentMeanService;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    public DataLoader(InvoiceService invoiceService, PaymentMeanService paymentMeanService) {
        this.invoiceService = invoiceService;
        this.paymentMeanService = paymentMeanService;
    }

    private final InvoiceService invoiceService;
    private final PaymentMeanService paymentMeanService;
    @Override
    public void run(String... args) throws Exception {
        Company compIssuer = Company.builder()
                .name("Company Issuer")
                .country("Russia")
                .address("Pishkina street")
                .email("java@java.com")
                .phone("12345678")
                .tin("4433222").build();

        Company compReceiver = Company.builder()
                .name("Company Receiver")
                .country("Russia")
                .address("Lermontova street")
                .email("123@mail.ru")
                .phone("12345678")
                .tin("4335567").build();

        UnitOfMeasure uomKG = UnitOfMeasure.builder()
                .name("KG")
                .description("Kilos").build();

        Good patotes = Good.builder()
                .name("Patotes")
                .uom(uomKG)
                .build();

        Set<Good> goods = new HashSet<>();
        goods.add(patotes);
        uomKG.setGoods(goods);

        PaymentMean pm1 = PaymentMean.builder()
                .description("Avans 10 %").build();

        PaymentMean pm2 = PaymentMean.builder()
                .description("not later than 30 days").build();

        //paymentMeanService.save(pm1);
        //paymentMeanService.save(pm2);

        Currency rub = Currency.getInstance("USD");

        InvoiceLine invL1 = InvoiceLine.builder()
                .good(patotes)
                .linenumber("0100")
                .quantity(new BigDecimal(1.00))
                .baseamount(new BigDecimal(100))
                .curr(rub)
                .build();

        HashSet<InvoiceLine> invLines = new HashSet<>();
        invLines.add(invL1);
        patotes.setInvoiceLines(invLines);

        HashSet<PaymentMean> pm = new HashSet<>();
        pm.add(pm1);
        pm.add(pm2);

        Invoice inv = Invoice.builder()
                .date(LocalDate.now())
                .companyissuer(compIssuer)
                .companyreceiver(compReceiver)
                .invoicelines(invLines)
                .paymentmeans(pm)
                .number("Inv1")
                .build();

        Set<Invoice> invoices = new HashSet<>();
        invoices.add(inv);
        pm1.setInvoices(invoices);
        pm2.setInvoices(invoices);

        invoiceService.save(inv);
    }
}
