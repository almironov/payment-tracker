package cz.bsc.homework.storage;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import cz.bsc.homework.constants.Constants;
import cz.bsc.homework.converter.PaymentUSDConverter;
import cz.bsc.homework.domain.PaymentTransactionDTO;


/**
 * Payment transaction storage
 *
 * @author Alexander Mironov
 */
public class PaymentTrackerStorage {

    private static volatile PaymentTrackerStorage instance;

    private Map<String, BigDecimal> paymentStorage = Collections.synchronizedMap(new LinkedHashMap<>());

    private final PaymentUSDConverter paymentUSDConverter = new PaymentUSDConverter();

    final NumberFormat formatter = new DecimalFormat("#0.00");

    private PaymentTrackerStorage() {}

    public static PaymentTrackerStorage getInstance() {
        if (instance == null) {
            synchronized (PaymentTrackerStorage.class) {
                if (instance == null) {
                    instance = new PaymentTrackerStorage();
                }
            }
        }
        return instance;
    }

    /**
     * Add new payment
     * @param paymentTransactionDTO
     */
    public void add(final PaymentTransactionDTO paymentTransactionDTO) {
        final BigDecimal amount = paymentTransactionDTO.getAmount();
        final BigDecimal bigDecimalZero = BigDecimal.ZERO;
        if (!amount.equals(bigDecimalZero)) {
            final String currency = paymentTransactionDTO.getCurrency();
            if (paymentStorage.get(currency) == null) { paymentStorage.put(currency, amount); } else {
                paymentStorage.computeIfPresent(currency, (k, v) -> v.add(amount));
            }
            if (paymentStorage.get(currency).equals(bigDecimalZero)) { paymentStorage.remove(currency); }
        }
    }

    /**
     * get storage Map
     * @return
     */
    public synchronized Map getPaymentStorage() {
        return paymentStorage;
    }

    /**
     * Print balance to console
     */
    public synchronized void printBalance() {
        System.out.println(Constants.ACTUAL_BALANCE);
        paymentStorage.entrySet().forEach(
                p -> {
                    if (paymentUSDConverter.ifUSDRateExist(p.getKey())) {
                        System.out.println(p
                                + "  (USD "
                                + formatter.format(paymentUSDConverter.getPaymentUSDConvert(p.getKey(), p.getValue()))
                                + ")");
                    } else { System.out.println(p); }
                });
    }
}


