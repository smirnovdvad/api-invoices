package sdv.spring.apiinvoices.exception;

public class InvoiceDuplicateNumber extends RuntimeException{

    public InvoiceDuplicateNumber(String message) {
        super(message);
    }
}
