package cz.bsc.homework.validator;

import java.util.Objects;

import cz.bsc.homework.constants.Constants;
import cz.bsc.homework.exceprion.PackageDeliveryException;


/**
 * Validator
 * @author Alexander Mironov
 */
public class PackageDeliveryValidator {

    public static final String WEIGHT_MATCHES = "\\d+(\\.\\d+)?";
    public static final String POST_CODE_MATCHES = "\\d{5}";
    public static final String WEIGHT_LIMIT_MATCHES = "\\d+(\\.\\d{1})?";
    public static final String FEE_MATCHES = "\\d+(\\.\\d{2})";

    /**
     * Validate package input
     * @param inputSplit
     */
    public void correctInput(String[] inputSplit){
        if (inputSplit.length != 2) {
            throw new PackageDeliveryException(Constants.LINE_MUST_CONTAIN_PKG_AND_ZIP);
        }
    }

    /**
     * Validate post code input
     * @param postCode
     */
    public void postCodeValidator(String postCode){
        if (postCode.length()!=5)
            throw new PackageDeliveryException(Constants.POSTCODE_MUST_CONTAIN_5_DIGITS);
        if (!postCode.matches(POST_CODE_MATCHES))
            throw new PackageDeliveryException(Constants.POSTCODE_MUST_CONTAIN_ONLY_NUMBERS);
    }

    /**
     * Validate weight of package input
     * @param weight
     */
    public void weightValidator(String weight){
        if (!weight.matches(WEIGHT_MATCHES))
            throw new PackageDeliveryException(Constants.INCORRECT_WEIGHT_INPUT_FORMAT);
    }

    /**
     * Validate weight fee limit input
     * @param weight
     */
    public void weightLimitValidator(String weight){
        if (!weight.matches(WEIGHT_LIMIT_MATCHES))
            throw new PackageDeliveryException(Constants.INCORRECT_WEIGHT_INPUT_FORMAT);
    }

    /**
     * Validate fee input
     * @param weight
     */
    public void feeValidator(String weight){
        if (!weight.matches(FEE_MATCHES))
            throw new PackageDeliveryException(Constants.INCORRECT_FEE_INPUT_FORMAT);
    }

    /**
     * Check null
     * @param input
     */
    public void objectsIsNull(String input){
        if (Objects.isNull(input))
            throw new PackageDeliveryException(Constants.OBJECT_IS_NULL);
    }
}
