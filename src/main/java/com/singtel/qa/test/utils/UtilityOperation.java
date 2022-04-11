package com.singtel.qa.test.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class UtilityOperation {

    public static double extractDecimal(String stringValue){
        return getOnePlaceDecimal(Double.parseDouble(stringValue.substring(0, stringValue.length() - 2)));
    }

    public static double getOnePlaceDecimal(Double doubleValue){
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return Double.parseDouble(decimalFormat.format(doubleValue));

    }

    public static int extractInteger(String stringValue) {
        return Integer.parseInt(stringValue.replaceAll("[^0-9]", ""));
    }
}
