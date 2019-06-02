package cz.bsc.homework;

import cz.bsc.homework.service.PaymentTrackerService;
import cz.bsc.homework.timer.PaymentBalanceTimer;


public class Main {

    public static void main(String[] args) {

        new PaymentBalanceTimer().timerPrintBalance();
        new PaymentTrackerService().startPayment();

    }
}
