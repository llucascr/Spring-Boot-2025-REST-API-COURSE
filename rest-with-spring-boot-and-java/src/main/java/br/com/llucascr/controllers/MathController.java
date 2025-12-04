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
