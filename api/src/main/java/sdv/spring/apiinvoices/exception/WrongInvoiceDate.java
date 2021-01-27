package sdv.spring.apiinvoices.exception;

public class WrongInvoiceDate extends RuntimeException{
    public WrongInvoiceDate(String message) {
        super(message);
    }
}
