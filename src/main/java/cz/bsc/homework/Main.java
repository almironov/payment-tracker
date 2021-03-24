package cz.bsc.homework;

import cz.bsc.homework.service.PackageDeliveryService;
import cz.bsc.homework.timer.PackageBalanceTimer;


public class Main {

    public static void main(String[] args) {

        new PackageBalanceTimer().timerPrintBalance();
        new PackageDeliveryService().startPayment();

    }
}
