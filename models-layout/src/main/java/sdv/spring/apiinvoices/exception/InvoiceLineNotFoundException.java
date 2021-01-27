package sdv.spring.apiinvoices.exception;

public class InvoiceLineNotFoundException extends RuntimeException{

    public InvoiceLineNotFoundException(String message) {
        super(message);
    }
}
