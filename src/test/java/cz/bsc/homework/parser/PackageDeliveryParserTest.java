package cz.bsc.homework.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.easymock.TestSubject;
import org.junit.Test;

import cz.bsc.homework.constants.Constants;
import cz.bsc.homework.domain.FeeDTO;
import cz.bsc.homework.domain.PackageDTO;
import cz.bsc.homework.exceprion.PackageDeliveryException;


/**
 * The test suite for packageTransactionParser.
 *
 * @author Alexander Mironov
 */
public class PackageDeliveryParserTest {

    @TestSubject
    private final PackageDeliveryParser packageParser = new PackageDeliveryParser();


    @Test
    public void packageParsingGoodTest() {
        PackageDTO packageDTOTest = packageParser.packageParsing("3.4 08801");
        assertThat(new PackageDTO("08801", new BigDecimal("3.4")),is(packageDTOTest));
    }



    @Test
    public void packageParsingTestIfInputNull() {
        try {
            packageParser.packageParsing(null);
            fail();
        } catch (PackageDeliveryException e) {
            assertThat(e.getMessage(), is(Constants.OBJECT_IS_NULL));
        }
    }

    @Test
    public void packageParsingTestIfZipNotCorrect() {
        try {
            packageParser.packageParsing("3.07 1234a");
            fail();
        } catch (PackageDeliveryException e) {
            assertThat(e.getMessage(), is(Constants.POSTCODE_MUST_CONTAIN_ONLY_NUMBERS));
        }
    }

    @Test
    public void packageParsingTestIfZipTooLong() {
        try {
            packageParser.packageParsing("3.07 123456");
            fail();
        } catch (PackageDeliveryException e) {
            assertThat(e.getMessage(), is(Constants.POSTCODE_MUST_CONTAIN_5_DIGITS));
        }
    }

    @Test
    public void packageParsingTestIfZipTooShort() {
        try {
            packageParser.packageParsing("3.0007 1234");
            fail();
        } catch (PackageDeliveryException e) {
            assertThat(e.getMessage(), is(Constants.POSTCODE_MUST_CONTAIN_5_DIGITS));
        }
    }

    @Test
    public void feeParsingTest() {
        FeeDTO feeDTO = packageParser.feesParsing("3.0 2.00");
        assertThat(new FeeDTO(new BigDecimal("3.0"), new BigDecimal("2.00")), is(feeDTO));
    }

    @Test
    public void feeParsingTestIfWeightLimitBadFormat() {
        try {
            packageParser.feesParsing("0.0001 3");
            fail();
        } catch (PackageDeliveryException e) {
            assertThat(e.getMessage(), is(Constants.INCORRECT_WEIGHT_INPUT_FORMAT));
        }
    }

    @Test
    public void feeParsingTestIfFeeBadFormat() {
        try {
            packageParser.feesParsing("3.0 3.222");
            fail();
        } catch (PackageDeliveryException e) {
            assertThat(e.getMessage(), is(Constants.INCORRECT_FEE_INPUT_FORMAT));
        }
    }


}
