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
            "Enter the currency and payment amount. The currency is always 3 letters in upper case and input the payment."
                    + " amount separated by a space.";
    public static final String ENTER_FILE_OPTION="For enter data from a file, enter 'file'.";
    public static final String ENTER_QUIT_APP="For quit the application enter 'quit'.";
    public static final String ENTER_FILE_PATH="Set the file path with name.";
    public static final String FILE_NOT_FOUND="File not found. Retype 'file' and then specify the path to the file.";
    public static final String FILE_COULD_NOT_BE_LOADED="\"The file could not be loaded because it contains errors.\"";

    //Constants for input line
    public static final String QUIT="quit";
    public static final String FILE="file";

    //Constants for Storage
    public static final String ACTUAL_BALANCE="Actual balance is: ";

    //Constants for cz.bsc.homework.timer
    public static final int TIMER_PERIOD_SECONDS=60;

    //Constants for cz.bsc.homework.validator
    public static final String LINE_MUST_CONTAIN_CURRENCY_AND_AMOUNT="The line must contain the currency and the amount separated by a space.";
    public static final String CURRENCY_MUST_HAVE_3_LETTERS="Currency must have 3 letters.";
    public static final String CURRENCY_MUST_BE_IN_UPPER_CASE ="All letters in the currency name must be in upper case.";
    public static final String AMOUNT_MUST_BE_A_NUMERIC_VALUE="Payment amount must be set to a numeric value.";
    public static final String OBJECT_IS_NULL="Object is null.";
}
