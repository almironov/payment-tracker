package cz.bsc.homework.parser;

import java.math.BigDecimal;

import cz.bsc.homework.domain.FeeDTO;
import cz.bsc.homework.domain.PackageDTO;
import cz.bsc.homework.validator.PackageDeliveryValidator;


/**
 * Class Parsing String to PackageDeliveryParser
 *
 * @author Alexander Mironov
 */
public class PackageDeliveryParser {

    private static final PackageDeliveryValidator lineValidator = new PackageDeliveryValidator();

    /**
     *
     * @param line - The line which contain the weight and the postcode
     * @return
     */
    public PackageDTO packageParsing(final String line) {
        lineValidator.objectsIsNull(line);
        final String[] resultSplit = line.trim().split(" ");
        lineValidator.correctInput(resultSplit);
        lineValidator.weightValidator(resultSplit[0]);
        lineValidator.postCodeValidator(resultSplit[1]);
        return new PackageDTO(resultSplit[1], new BigDecimal(resultSplit[0]));
    }

    public FeeDTO feesParsing(final String line) {
        lineValidator.objectsIsNull(line);
        final String[] resultSplit = line.trim().split(" ");
        lineValidator.correctInput(resultSplit);
        lineValidator.weightLimitValidator(resultSplit[0]);
        lineValidator.feeValidator(resultSplit[1]);
        return new FeeDTO(new BigDecimal(resultSplit[0]), new BigDecimal(resultSplit[1]));
    }

}
