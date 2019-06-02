package cz.bsc.homework.exceprion;

/**
 * Exception for payment transaction
 *
 * @author Alexander Mironov
 */
public class PaymentTransactionException extends RuntimeException {

    public PaymentTransactionException(final String message) {
        super(message);
    }

}
