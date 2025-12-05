package br.com.llucascr.request.converters;

import br.com.llucascr.exception.UnsupportedMathOperationException;

public class NumberConverter {

    public static boolean isNumberic(String strNumber) {
        if (strNumber == null || strNumber.isEmpty())  return false;
        String number = strNumber.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public static Double convertToDouble(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) throw new UnsupportedMathOperationException("Please set a numeric value");
        return Double.parseDouble(strNumber);
    }

}
