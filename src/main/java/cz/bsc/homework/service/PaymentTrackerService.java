package cz.bsc.homework.service;

import cz.bsc.homework.constants.Constants;
import cz.bsc.homework.exceprion.PaymentTransactionException;
import cz.bsc.homework.parser.PaymentTransactionParser;
import cz.bsc.homework.storage.PaymentTrackerStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Class Service Payment Transaction
 *
 * @author Alexander Mironov
 */
public class PaymentTrackerService {

    private final PaymentTransactionParser paymentTransactionParser = new PaymentTransactionParser();
    private final PaymentTrackerStorage paymentTrackerStorage = PaymentTrackerStorage.getInstance();
    private final Scanner in = new Scanner(System.in);

    /**
     * Start payment tracker
     */
    public void startPayment() {

        System.out.print(Constants.ENTER_CURRENCY_AND_PAYMENT_AMOUNT + "\n"
                + Constants.ENTER_FILE_OPTION + "\n"
                + Constants.ENTER_QUIT_APP + "\n");

        try {
            while (in.hasNextLine()) {

                final String input = in.nextLine().trim();
                if (input.equals(Constants.QUIT)) {
                    System.exit(0);
                    in.close();
                }
                if (input.equals(Constants.FILE)) {
                    fileInput();
                    continue;
                }
                try {
                    addPayment(input);
                } catch (PaymentTransactionException e) {
                    System.err.println(e.getMessage());
                }
            }
        } finally {
            in.close();
        }

    }

    /**
     * Add new payment
     * @param input - Payment input line
     */
    private void addPayment(final String input) {
        paymentTrackerStorage.add(paymentTransactionParser.paymentParsing(input));
    }

    /**
     * Enter payment from file
     */
    private void fileInput() {
        System.out.println(Constants.ENTER_FILE_PATH);
        final String fileLink = in.nextLine().trim();

        try (Stream<String> stream = Files.lines(Paths.get(fileLink))) {
            stream.map(paymentTransactionParser::paymentParsing)
                    .collect(Collectors.toList())
                    .forEach(paymentTrackerStorage::add);
        } catch (IOException e) {
            System.err.println(Constants.FILE_NOT_FOUND);
            System.err.println(e.getMessage());
        } catch (PaymentTransactionException ptex) {
            System.err.println(Constants.FILE_COULD_NOT_BE_LOADED);
            System.err.println(ptex.getMessage());
        }


    }
}
