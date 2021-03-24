package cz.bsc.homework.constants;

/**
 * @author Alexander Mironov
 */
public class Constants {

    private Constants() {
        throw new IllegalStateException("Constants class");
    }

    // Constants for Service
    public static final String ENTER_CURRENCY_AND_PAYMENT_AMOUNT=
            "Enter the weight of package and destination postal code. The postcode is always 5 digits."
                    + " weight and postcode separated by a space.";
    public static final String ENTER_FILE_OPTION="For enter packages data from a file, type 'file'.";
    public static final String ENTER_FEES_FILE_OPTION="For enter fees data from a file, type 'fees'.";
    public static final String ENTER_QUIT_APP="For quit the application enter 'quit'.";
    public static final String ENTER_FILE_PATH="Set the path to the file with the name that contains the parcels.";
    public static final String ENTER_FEES_FILE_PATH="Set the path to the file with the name that contains the fees.";
    public static final String FILE_NOT_FOUND="File not found. Retype 'file' and then specify the path to the file.";
    public static final String FILE_COULD_NOT_BE_LOADED="\"The file could not be loaded because it contains errors.\"";

    //Constants for input line
    public static final String QUIT="quit";
    public static final String FILE_PKG="file";
    public static final String FILE_FEES="fees";

    //Constants for Storage
    public static final String ACTUAL_BALANCE="Balance of packages: ";

    //Constants for cz.bsc.homework.timer
    public static final int TIMER_PERIOD_SECONDS=60;

    //Constants for cz.bsc.homework.validator
    public static final String LINE_MUST_CONTAIN_PKG_AND_ZIP = "The line must contain the package weight and the postcode separated by a space.";
    public static final String POSTCODE_MUST_CONTAIN_5_DIGITS = "Postcode should contain 5 digits, no space.";
    public static final String POSTCODE_MUST_CONTAIN_ONLY_NUMBERS = "The postcode should only contain numbers.";
    public static final String INCORRECT_WEIGHT_INPUT_FORMAT = "Weight input format was incorrect.";
    public static final String INCORRECT_FEE_INPUT_FORMAT = "Fee input format was incorrect.";
    public static final String OBJECT_IS_NULL = "Object is null.";
}
