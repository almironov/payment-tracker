package cz.bsc.homework.validator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.easymock.TestSubject;
import org.junit.Test;

import cz.bsc.homework.constants.Constants;
import cz.bsc.homework.exceprion.PaymentTransactionException;


/**
 * The test suite for PaymentTransactionValidator.
 *
 * @author Alexander Mironov
 */
public class PaymentTransactionValidatorTest {
    @TestSubject
    private final PaymentTransactionValidator paymentValidator = new PaymentTransactionValidator();

    @Test
    public void correctInputGoodTest() {
        final String[] testInput= {"USD", "100"};
        paymentValidator.correctInput(testInput);
    }
    @Test
    public void correctInputMoreThenTwoElements() {
        final String[] testInput= {"USD", "100","100"};
        try {
            paymentValidator.correctInput(testInput);
            fail();
        } catch (PaymentTransactionException e) {
            assertThat(e.getMessage(), is(Constants.LINE_MUST_CONTAIN_CURRENCY_AND_AMOUNT));
        }
    }
    @Test
    public void correctInputOneElements() {
        final String[] testInput= {"USD"};
        try {
            paymentValidator.correctInput(testInput);
            fail();
        } catch (PaymentTransactionException e) {
            assertThat(e.getMessage(), is(Constants.LINE_MUST_CONTAIN_CURRENCY_AND_AMOUNT));
        }
    }

    @Test
    public void currencyValidatorGoodTest() {
        paymentValidator.currencyValidator("USD");
    }

    @Test
    public void currencyValidatorLessThenThreeLetters() {
        try {
            paymentValidator.currencyValidator("US");
            fail();
        } catch (PaymentTransactionException e) {
            assertThat(e.getMessage(), is(Constants.CURRENCY_MUST_HAVE_3_LETTERS));
        }
    }

    @Test
    public void currencyValidatorMoreThenThreeLetters() {
        try {
            paymentValidator.currencyValidator("USDD");
            fail();
        } catch (PaymentTransactionException e) {
            assertThat(e.getMessage(), is(Constants.CURRENCY_MUST_HAVE_3_LETTERS));
        }
    }

    @Test
    public void currencyValidatorNotAllLettersUpperCase() {
        try {
            paymentValidator.currencyValidator("USs");
            fail();
        } catch (PaymentTransactionException e) {
            assertThat(e.getMessage(), is(Constants.CURRENCY_MUST_BE_IN_UPPER_CASE));
        }
    }

    @Test
    public void amountValidatorGoodTest() {
        paymentValidator.amountValidator("100");
    }

    @Test
    public void amountValidatorGoodTestNumbersWithMinus() {
        paymentValidator.amountValidator("-100.55");
    }

    @Test
    public void amountValidatorBadTestNumbersWithMinus() {
        try {
            paymentValidator.amountValidator("100.55-");
            fail();
        } catch (PaymentTransactionException e) {
            assertThat(e.getMessage(), is(Constants.AMOUNT_MUST_BE_A_NUMERIC_VALUE));
        }
    }


}