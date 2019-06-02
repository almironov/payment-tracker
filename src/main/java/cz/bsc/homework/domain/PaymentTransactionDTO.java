package cz.bsc.homework.domain;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * Class DTO Payment transaction
 *
 * @author Alexander Mironov
 */
public class PaymentTransactionDTO {
    private final String currency;
    private final BigDecimal amount;


    public PaymentTransactionDTO(final String currency, final BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        PaymentTransactionDTO that = (PaymentTransactionDTO) o;

        if (!Objects.equals(currency, that.currency)) { return false; }
        return Objects.equals(amount, that.amount);

    }

    @Override
    public int hashCode() {
        int result = currency != null ? currency.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

}
