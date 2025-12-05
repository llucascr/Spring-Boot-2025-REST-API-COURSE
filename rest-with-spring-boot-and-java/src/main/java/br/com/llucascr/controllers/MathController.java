package br.com.llucascr.controllers;

import br.com.llucascr.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    // http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {

        if (!isNumberic(numberOne) || !isNumberic(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value");

        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    // http://localhost:8080/math/subtraction/3/5
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (!isNumberic(numberOne) || !isNumberic(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value");

        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    // http://localhost:8080/math/multiplication/3/5
    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (!isNumberic(numberOne) || !isNumberic(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value");

        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    // http://localhost:8080/math/division/3/5
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (!isNumberic(numberOne) || !isNumberic(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value");

        Double numberOneDouble = convertToDouble(numberOne);
        Double numberTwoDouble = convertToDouble(numberTwo);

        if (numberOneDouble == 0 || numberTwoDouble == 0) throw new UnsupportedMathOperationException("It is not possible to divide by zero");

        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    // http://localhost:8080/math/mean/3/5
    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (!isNumberic(numberOne) || !isNumberic(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value");

        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2.0;
    }

    // http://localhost:8080/math/squareRoot/81
    @RequestMapping("/squareRoot/{numberOne}")
    public Double squareRoot(
            @PathVariable("numberOne") String number
    ) {
        if (!isNumberic(number)) throw new UnsupportedMathOperationException("Please set a numeric value");

        return Math.sqrt(convertToDouble(number));
    }

    private boolean isNumberic(String strNumber) {
        if (strNumber == null || strNumber.isEmpty())  return false;
        String number = strNumber.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private Double convertToDouble(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) throw new UnsupportedMathOperationException("Please set a numeric value");
        return Double.parseDouble(strNumber);
    }


}
