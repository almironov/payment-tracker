package cz.bsc.homework.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cz.bsc.homework.constants.Constants;
import cz.bsc.homework.storage.PackageDeliveryStorage;


/**
 * Timer - print balance
 *
 * @author Alexander Mironov
 */

public class PackageBalanceTimer {

    private final PackageDeliveryStorage storage = PackageDeliveryStorage.getInstance();

    public void timerPrintBalance() {
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        final Runnable task = storage::printBalance;

        final int initialDelay = 60;
        executor.scheduleAtFixedRate(task, initialDelay, Constants.TIMER_PERIOD_SECONDS, TimeUnit.SECONDS);
    }

}

