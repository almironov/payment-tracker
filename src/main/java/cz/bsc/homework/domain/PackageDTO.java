package cz.bsc.homework.domain;

import java.math.BigDecimal;


/**
 * Class information
 *
 * @author Alexander Mironov
 */
public class PackageDTO {
    private String postCode;
    private BigDecimal weight;

    public PackageDTO(String postCode, BigDecimal weight) {
        this.postCode = postCode;
        this.weight = weight;
    }

    public String getPostCode() {
        return postCode;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        PackageDTO that = (PackageDTO) o;

        if (postCode != null ? !postCode.equals(that.postCode) : that.postCode != null) { return false; }
        return weight != null ? weight.equals(that.weight) : that.weight == null;
    }

    @Override
    public int hashCode() {
        int result = postCode != null ? postCode.hashCode() : 0;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }
}
