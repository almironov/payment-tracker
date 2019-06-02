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

import cz.bsc.homework.domain.PaymentTransactionDTO;


/**
 * The test suite for PaymentTrackerStorage}.
 *
 * @author Alexander Mironov
 */
public class PaymentTrackerStorageTest {

    @TestSubject
    private final PaymentTrackerStorage paymentTrackerStorage = PaymentTrackerStorage.getInstance();

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
        paymentTrackerStorage.add(new PaymentTransactionDTO("USD", BigDecimal.valueOf(100)));
        paymentTrackerStorage.add(new PaymentTransactionDTO("EUR", BigDecimal.valueOf(200)));
        paymentTrackerStorage.add(new PaymentTransactionDTO("RUB", BigDecimal.valueOf(-200)));
        paymentTrackerStorage.add(new PaymentTransactionDTO("USD", BigDecimal.valueOf(100)));
        paymentTrackerStorage.add(new PaymentTransactionDTO("EUR", BigDecimal.valueOf(200)));
        paymentTrackerStorage.add(new PaymentTransactionDTO("EUR", BigDecimal.valueOf(200)));
        paymentTrackerStorage.add(new PaymentTransactionDTO("RUB", BigDecimal.valueOf(200)));
        paymentTrackerStorage.add(new PaymentTransactionDTO("RUB", BigDecimal.valueOf(-200)));
        paymentTrackerStorage.add(new PaymentTransactionDTO("RUB", BigDecimal.valueOf(55.5555)));

        assertThat(paymentTrackerStorage.getPaymentStorage().get("EUR"), is(BigDecimal.valueOf(800)));
        assertThat(paymentTrackerStorage.getPaymentStorage().get("USD"), is(BigDecimal.valueOf(300)));
        assertThat(paymentTrackerStorage.getPaymentStorage().get("RUB"), is(BigDecimal.valueOf(-344.4445)));

    }

    @Test
    public void printBalance() {
        final String testOutputBalance = "Actual balance is: \nUSD=100\nEUR=200  (USD 223.79)\nRUB=-200  (USD -3.05)\nXXX=6200\n";

        paymentTrackerStorage.add(new PaymentTransactionDTO("USD", BigDecimal.valueOf(100)));
        paymentTrackerStorage.add(new PaymentTransactionDTO("EUR", BigDecimal.valueOf(200)));
        paymentTrackerStorage.add(new PaymentTransactionDTO("RUB", BigDecimal.valueOf(-200)));
        paymentTrackerStorage.add(new PaymentTransactionDTO("XXX", BigDecimal.valueOf(6200)));
        paymentTrackerStorage.printBalance();

        assertThat(testOutputBalance, is(outContent.toString()));

    }
}