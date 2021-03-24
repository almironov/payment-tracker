package cz.bsc.homework.storage;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.base.Functions;
import com.google.common.collect.Ordering;

import cz.bsc.homework.constants.Constants;
import cz.bsc.homework.domain.FeeDTO;
import cz.bsc.homework.domain.PackageDTO;


/**
 * PackageDeliveryStorage
 *
 * @author Alexander Mironov
 */
public class PackageDeliveryStorage {

    private static volatile PackageDeliveryStorage instance;
    //Storage cache
    private Map<String, BigDecimal> packagesByPostcodeStorage = Collections.synchronizedMap(new HashMap<>());
    private Map<String, BigDecimal> feesByPostcodeStorage = Collections.synchronizedMap(new HashMap<>());
    private Map<BigDecimal, BigDecimal> feesRelated = new TreeMap<>();

    private static final NumberFormat FORMATTER_WEIGHT = new DecimalFormat("#0.000");
    private static final NumberFormat FORMATTER_FEE = new DecimalFormat("#0.00");

    private PackageDeliveryStorage() {
    }

    public static synchronized PackageDeliveryStorage getInstance() {
            if (instance == null) {
                instance = new PackageDeliveryStorage();
            }
        return instance;
    }

    /**
     * Add new package
     * @param packageDTO
     */
    public void add(final PackageDTO packageDTO) {
        final BigDecimal weight = packageDTO.getWeight();
        final BigDecimal bigDecimalZero = BigDecimal.ZERO;
        if (!weight.equals(bigDecimalZero)) {
            final String postCode = packageDTO.getPostCode();
            if (packagesByPostcodeStorage.get(postCode) == null) { packagesByPostcodeStorage.put(postCode, weight); } else {
                packagesByPostcodeStorage.computeIfPresent(postCode, (k, v) -> v.add(weight));
            }
            if (!feesRelated.isEmpty()) {
                addFeesRate(packageDTO);
            }
        }
    }

    /**
     * get storage Map
     * @return
     */
    public synchronized Map<String, BigDecimal> getPackagesByPostcodeStorage() {
        return packagesByPostcodeStorage;
    }

    /**
     * Add new fee
     * @param packageDTO
     */
    private void addFeesRate(final PackageDTO packageDTO) {
        AtomicReference<BigDecimal> feeResult = new AtomicReference<>();
        for (Map.Entry<BigDecimal, BigDecimal> entry : feesRelated.entrySet()) {
            BigDecimal key = entry.getKey();
            BigDecimal value = entry.getValue();
            final BigDecimal weight = packageDTO.getWeight();
            if (key.compareTo(weight) >= 0) {
                if (key.compareTo(weight) == 0) {
                    feeResult.set(value);
                }
                break;
            } else {
                feeResult.set(value);
            }
        }
        final String postCode = packageDTO.getPostCode();
        if (feeResult.get() != null) {
            if (feesByPostcodeStorage.get(postCode) == null) {
                feesByPostcodeStorage.put(postCode, feeResult.get());
            } else {
                feesByPostcodeStorage.computeIfPresent(postCode, (k, v) -> v.add(feeResult.get()));
            }
        }
    }

    public void addFeeRelated(FeeDTO feeDTO){
        feesRelated.put(feeDTO.getWeightLimit(), feeDTO.getFee());
    }

    /**
     * Print balance of packages to console
     */
    public synchronized void printBalance() {
        System.out.println(Constants.ACTUAL_BALANCE);
        packagesByPostcodeStorage.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(p -> {
                    if (feesByPostcodeStorage.get(p.getKey()) != null) {
                        System.out.println(p.getKey()
                                + " " + FORMATTER_WEIGHT.format(p.getValue())
                                + " " + FORMATTER_FEE.format(feesByPostcodeStorage.get(p.getKey()))
                        );
                    } else {
                        System.out.println(p.getKey()
                                + " " + FORMATTER_WEIGHT.format(p.getValue())
                        );
                    }
                });
    }
}


