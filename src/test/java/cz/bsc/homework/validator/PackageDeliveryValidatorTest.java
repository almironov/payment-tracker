package cz.bsc.homework.validator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.easymock.TestSubject;
import org.junit.Test;

import cz.bsc.homework.constants.Constants;
import cz.bsc.homework.exceprion.PackageDeliveryException;


/**
 * The test suite for PaymentTransactionValidator.
 *
 * @author Alexander Mironov
 */
public class PackageDeliveryValidatorTest {
    @TestSubject
    private final PackageDeliveryValidator paymentValidator = new PackageDeliveryValidator();

    @Test
    public void correctInputGoodTest() {
        final String[] testInput= {"3.4", "00011"};
        paymentValidator.correctInput(testInput);
    }
    @Test
    public void correctInputMoreThenTwoElements() {
        final String[] testInput= {"5.5", "00001","5"};
        try {
            paymentValidator.correctInput(testInput);
            fail();
        } catch (PackageDeliveryException e) {
            assertThat(e.getMessage(), is(Constants.LINE_MUST_CONTAIN_PKG_AND_ZIP));
        }
    }
    @Test
    public void correctInputOneElements() {
        final String[] testInput= {"08801"};
        try {
            paymentValidator.correctInput(testInput);
            fail();
        } catch (PackageDeliveryException e) {
            assertThat(e.getMessage(), is(Constants.LINE_MUST_CONTAIN_PKG_AND_ZIP));
        }
    }

    @Test
    public void postcodeValidatorGoodTest() {
        paymentValidator.postCodeValidator("01110");
    }

    @Test
    public void postcodeValidatorLessThenFiveDigits() {
        try {
            paymentValidator.postCodeValidator("0880");
            fail();
        } catch (PackageDeliveryException e) {
            assertThat(e.getMessage(), is(Constants.POSTCODE_MUST_CONTAIN_5_DIGITS));
        }
    }

    @Test
    public void postcodeValidatorNotOnlyDigits() {
        try {
            paymentValidator.postCodeValidator("00a00");
            fail();
        } catch (PackageDeliveryException e) {
            assertThat(e.getMessage(), is(Constants.POSTCODE_MUST_CONTAIN_ONLY_NUMBERS));
        }
    }

    @Test
    public void weightIntegerTest() {
        paymentValidator.weightValidator("5");
    }

    @Test
    public void weightFloatTest() {
        paymentValidator.weightValidator("1.5555");
    }

    @Test
    public void testNumbersWithMinus() {
        try {
            paymentValidator.weightValidator("-1.55");
            fail();
        } catch (PackageDeliveryException e) {
            assertThat(e.getMessage(), is(Constants.INCORRECT_WEIGHT_INPUT_FORMAT));
        }
    }


}
