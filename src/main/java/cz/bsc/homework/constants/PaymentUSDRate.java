package cz.bsc.homework.constants;

import java.math.BigDecimal;


/**
 * enum contains actual USD rate from 02.06.2019
 */
public enum PaymentUSDRate {
    CZK(new BigDecimal(0.0432143544)),
    EUR(new BigDecimal(1.1189664066)),
    RUB(new BigDecimal(0.0152461232)),
    CAD(new BigDecimal(0.7397203824)),
    INR(new BigDecimal(0.0143788708)),
    MXN(new BigDecimal(0.0509792511)),
    AUD(new BigDecimal(0.6929949620)),
    CNY(new BigDecimal(0.1448205603)),
    MYR(new BigDecimal(0.2385949961)),
    COP(new BigDecimal(0.0002963012)),
    SGD(new BigDecimal(0.7278445402)),
    CHF(new BigDecimal(1.0002320687)),
    JPY(new BigDecimal(0.0092359143)),
    NZD(new BigDecimal(0.6534139477)),
    THB(new BigDecimal(0.0317393448)),
    HUF(new BigDecimal(0.0034377045)),
    AED(new BigDecimal(0.2722940776)),
    HKD(new BigDecimal(0.1275633065)),
    ZAR(new BigDecimal(0.0686818814)),
    GBR(new BigDecimal(1.2615176317));

    private BigDecimal paymentRate;

    PaymentUSDRate(BigDecimal paymentRate) {
        this.paymentRate = paymentRate;
    }

    public BigDecimal getPaymentRate() {
        return paymentRate;
    }
}
