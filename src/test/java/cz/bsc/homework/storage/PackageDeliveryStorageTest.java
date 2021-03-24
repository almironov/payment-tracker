package cz.bsc.homework.storage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cz.bsc.homework.domain.PackageDTO;


/**
 * The test suite for PaymentTrackerStorage}.
 *
 * @author Alexander Mironov
 */
public class PackageDeliveryStorageTest {

    @TestSubject
    private final PackageDeliveryStorage packageDeliveryStorage = PackageDeliveryStorage.getInstance();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void addNewPayment() {
        packageDeliveryStorage.add(new PackageDTO("08080", BigDecimal.valueOf(7.001)));
        packageDeliveryStorage.add(new PackageDTO("09090", BigDecimal.valueOf(2.1)));
        packageDeliveryStorage.add(new PackageDTO("00004", BigDecimal.valueOf(4.03)));
        packageDeliveryStorage.add(new PackageDTO("90000", BigDecimal.valueOf(5.0004)));
        packageDeliveryStorage.add(new PackageDTO("77777", BigDecimal.valueOf(2.1)));
        packageDeliveryStorage.add(new PackageDTO("77777", BigDecimal.valueOf(1)));
        packageDeliveryStorage.add(new PackageDTO("60000", BigDecimal.valueOf(5)));
        packageDeliveryStorage.add(new PackageDTO("09090", BigDecimal.valueOf(6.6666)));

        assertThat(packageDeliveryStorage.getPackagesByPostcodeStorage().get("90000"), is(BigDecimal.valueOf(5.0004)));
        assertThat(packageDeliveryStorage.getPackagesByPostcodeStorage().get("09090"), is(BigDecimal.valueOf(8.7666)));
        assertThat(packageDeliveryStorage.getPackagesByPostcodeStorage().get("77777"), is(BigDecimal.valueOf(3.1)));
        assertThat(packageDeliveryStorage.getPackagesByPostcodeStorage().get("60000"), is(BigDecimal.valueOf(5)));
        assertThat(packageDeliveryStorage.getPackagesByPostcodeStorage().get("00004"), is(BigDecimal.valueOf(4.03)));
        assertThat(packageDeliveryStorage.getPackagesByPostcodeStorage().get("08080"), is(BigDecimal.valueOf(7.001)));

    }

    @Test
    public void printBalance() {
        final String testOutputBalance = "Balance of packages: \n00001 9,505\n00002 4,050\n99999 2,002\n";

        packageDeliveryStorage.add(new PackageDTO("00001", BigDecimal.valueOf(6.5)));
        packageDeliveryStorage.add(new PackageDTO("00002", BigDecimal.valueOf(4.05)));
        packageDeliveryStorage.add(new PackageDTO("00001", BigDecimal.valueOf(3.005)));
        packageDeliveryStorage.add(new PackageDTO("99999", BigDecimal.valueOf(2.0016)));
        packageDeliveryStorage.printBalance();

        assertThat(testOutputBalance, is(outContent.toString()));
        cleanUpStreams();

    }
}
