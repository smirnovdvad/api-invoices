package sdv.spring.apiinvoices.exception;

public class InvoiceNotFoundException extends RuntimeException{

    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
