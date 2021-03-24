package cz.bsc.homework.domain;

import java.math.BigDecimal;


/**
 * Class information
 *
 * @author Alexander Mironov
 */
public class FeeDTO {
    private BigDecimal weightLimit;
    private BigDecimal fee;

    public FeeDTO(BigDecimal weightLimit, BigDecimal fee) {
        this.weightLimit = weightLimit;
        this.fee = fee;
    }

    public BigDecimal getWeightLimit() {
        return weightLimit;
    }

    public BigDecimal getFee() {
        return fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        FeeDTO feeDTO = (FeeDTO) o;

        if (weightLimit != null ? !weightLimit.equals(feeDTO.weightLimit) : feeDTO.weightLimit != null) { return false; }
        return fee != null ? fee.equals(feeDTO.fee) : feeDTO.fee == null;
    }

    @Override
    public int hashCode() {
        int result = weightLimit != null ? weightLimit.hashCode() : 0;
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        return result;
    }
}
