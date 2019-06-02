package cz.bsc.homework.parser;

import java.math.BigDecimal;

import cz.bsc.homework.domain.PaymentTransactionDTO;
import cz.bsc.homework.validator.PaymentTransactionValidator;


/**
 * Class Parsing String to PaymentTransactionDTO
 *
 * @author Alexander Mironov
 */
public class PaymentTransactionParser {

    private static final PaymentTransactionValidator transactionValidator = new PaymentTransactionValidator();

    /**
     *
     * @param transaction - The line which contain the currency and the amount
     * @return
     */
    public PaymentTransactionDTO paymentParsing(final String transaction) {
        transactionValidator.objectsIsNull(transaction);
        final String[] resultSplit = transaction.trim().split(" ");
        transactionValidator.correctInput(resultSplit);
        transactionValidator.currencyValidator(resultSplit[0]);
        transactionValidator.amountValidator(resultSplit[1]);
        return new PaymentTransactionDTO(resultSplit[0], new BigDecimal(resultSplit[1]));

    }

}
