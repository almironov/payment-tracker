package cz.bsc.homework.validator;

import java.util.Objects;

import com.google.common.base.CharMatcher;
import cz.bsc.homework.constants.Constants;
import cz.bsc.homework.exceprion.PaymentTransactionException;


/**
 * Validator
 * @author Alexander Mironov
 */
public class PaymentTransactionValidator {
    /**
     * Validate payment input
     * @param inputSplit
     */
    public void correctInput(String[] inputSplit){
        if (inputSplit.length != 2) {
            throw new PaymentTransactionException(Constants.LINE_MUST_CONTAIN_CURRENCY_AND_AMOUNT);
        }
    }

    /**
     * Validate currency input
     * @param currency
     */
    public void currencyValidator(String currency){
        if (currency.length()!=3)
            throw new PaymentTransactionException(Constants.CURRENCY_MUST_HAVE_3_LETTERS);
        if (!CharMatcher.JAVA_UPPER_CASE.matchesAllOf(currency))
            throw new PaymentTransactionException(Constants.CURRENCY_MUST_BE_IN_UPPER_CASE);
    }

    /**
     * Validate amount input
     * @param amount
     */
    public void amountValidator(String amount){
        if (!isNumeric(amount))
            throw new PaymentTransactionException(Constants.AMOUNT_MUST_BE_A_NUMERIC_VALUE);

    }

    /**
     * Match a number with optional '-' and decimal.
     * @param str
     * @return
     */
    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Check null
     * @param input
     */
    public void objectsIsNull(String input){
        if (Objects.isNull(input))
            throw new PaymentTransactionException(Constants.OBJECT_IS_NULL);
    }
}
