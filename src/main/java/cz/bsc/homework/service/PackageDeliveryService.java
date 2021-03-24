package cz.bsc.homework.service;

import cz.bsc.homework.constants.Constants;
import cz.bsc.homework.exceprion.PackageDeliveryException;
import cz.bsc.homework.parser.PackageDeliveryParser;
import cz.bsc.homework.storage.PackageDeliveryStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Class PackageDeliveryService
 *
 * @author Alexander Mironov
 */
public class PackageDeliveryService {

    private final PackageDeliveryParser packageDeliveryParser = new PackageDeliveryParser();
    private final PackageDeliveryStorage packageDeliveryStorage = PackageDeliveryStorage.getInstance();
    private final Scanner in = new Scanner(System.in);

    /**
     * Start payment tracker
     */
    public void startPayment() {

        System.out.print(Constants.ENTER_CURRENCY_AND_PAYMENT_AMOUNT + "\n"
                + Constants.ENTER_FILE_OPTION + "\n"
                + Constants.ENTER_FEES_FILE_OPTION + "\n"
                + Constants.ENTER_QUIT_APP + "\n");

        try {
            while (in.hasNextLine()) {

                final String input = in.nextLine().trim();
                if (input.equals(Constants.QUIT)) {
                    System.exit(0);
                    in.close();
                }
                if (input.equals(Constants.FILE_PKG)) {
                    fileInput();
                    continue;
                }
                if (input.equals(Constants.FILE_FEES)) {
                    feesFileInput();
                    continue;
                }
                try {
                    addPackage(input);
                } catch (PackageDeliveryException e) {
                    System.err.println(e.getMessage());
                }
            }
        } finally {
            in.close();
        }

    }

    /**
     * Add new package
     * @param input - Package input line
     */
    private void addPackage(final String input) {
        packageDeliveryStorage.add(packageDeliveryParser.packageParsing(input));
    }

    /**
     * Enter packages from file
     */
    private void fileInput() {
        System.out.println(Constants.ENTER_FILE_PATH);
        final String fileLink = in.nextLine().trim();

        try (Stream<String> stream = Files.lines(Paths.get(fileLink))) {
            stream.map(packageDeliveryParser::packageParsing)
                    .collect(Collectors.toList())
                    .forEach(packageDeliveryStorage::add);
        } catch (IOException e) {
            System.err.println(Constants.FILE_NOT_FOUND);
            System.err.println(e.getMessage());
        } catch (PackageDeliveryException ptex) {
            System.err.println(Constants.FILE_COULD_NOT_BE_LOADED);
            System.err.println(ptex.getMessage());
        }
    }

    /**
     * Enter payment from fees file
     */
    private void feesFileInput() {
        System.out.println(Constants.ENTER_FEES_FILE_PATH);
        final String fileLink = in.nextLine().trim();

        try (Stream<String> stream = Files.lines(Paths.get(fileLink))) {
            stream.map(packageDeliveryParser::feesParsing)
                    .collect(Collectors.toList())
                    .forEach(packageDeliveryStorage::addFeeRelated);
        } catch (IOException e) {
            System.err.println(Constants.FILE_NOT_FOUND);
            System.err.println(e.getMessage());
        } catch (PackageDeliveryException ptex) {
            System.err.println(Constants.FILE_COULD_NOT_BE_LOADED);
            System.err.println(ptex.getMessage());
        }
    }
}
