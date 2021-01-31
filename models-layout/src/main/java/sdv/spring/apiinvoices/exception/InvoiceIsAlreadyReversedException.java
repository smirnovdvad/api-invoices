package sdv.spring.apiinvoices.exception;

public class InvoiceIsAlreadyReversedException extends RuntimeException{

    public InvoiceIsAlreadyReversedException(String message) {
        super(message);
    }
}