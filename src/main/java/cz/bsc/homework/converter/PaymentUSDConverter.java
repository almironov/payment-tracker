package cz.bsc.homework.converter;

import java.math.BigDecimal;
import java.util.Arrays;

import cz.bsc.homework.constants.PaymentUSDRate;


/**
 * Convert to USD rate
 *
 * @author Alexander Mironov
 */
public class PaymentUSDConverter {

    /**
     * Payment USD convert
     *
     * @param currency
     * @param amount
     * @return amount multiply rate
     */
    public BigDecimal getPaymentUSDConvert(final String currency, final BigDecimal amount) {
        return PaymentUSDRate.valueOf(currency).getPaymentRate().multiply(amount);
    }

    /**
     * if USD rate exist
     *
     * @param rate Currency
     * @return true-if exist rate
     */
    public Boolean ifUSDRateExist(final String rate) {
        return Arrays.asList(PaymentUSDRate.values()).toString().contains(rate);
    }
}


