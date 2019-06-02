package cz.bsc.homework.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.easymock.TestSubject;
import org.junit.Test;

import cz.bsc.homework.constants.Constants;
import cz.bsc.homework.domain.PaymentTransactionDTO;
import cz.bsc.homework.exceprion.PaymentTransactionException;


/**
 * The test suite for PaymentTransactionParser.
 *
 * @author Alexander Mironov
 */
public class PaymentTransactionParserTest {

    @TestSubject
    private final PaymentTransactionParser paymentPrarser = new PaymentTransactionParser();


    @Test
    public void paymentParsingGoodTest() {
        PaymentTransactionDTO transactionDTOTest = paymentPrarser.paymentParsing("USD -100.678");
        assertThat(new PaymentTransactionDTO("USD", BigDecimal.valueOf(-100.678)),is(transactionDTOTest));
    }

    @Test
    public void paymentParsingTestIfInputNull() {
        try {
            PaymentTransactionDTO transactionDTOTest = paymentPrarser.paymentParsing(null);
            fail();
        } catch (PaymentTransactionException e) {
            assertThat(e.getMessage(), is(Constants.OBJECT_IS_NULL));
        }
    }
}