package br.com.llucascr.controllers;

import br.com.llucascr.exception.UnsupportedMathOperationException;
import br.com.llucascr.math.SimpleMath;
import br.com.llucascr.request.converters.NumberConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    private SimpleMath simpleMath = new SimpleMath();

    // http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {

        if (!NumberConverter.isNumberic(numberOne) || !NumberConverter.isNumberic(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");

        return simpleMath.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    // http://localhost:8080/math/subtraction/3/5
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (!NumberConverter.isNumberic(numberOne) || !NumberConverter.isNumberic(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");

        return simpleMath.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    // http://localhost:8080/math/multiplication/3/5
    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (!NumberConverter.isNumberic(numberOne) || !NumberConverter.isNumberic(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");

        return simpleMath.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    // http://localhost:8080/math/division/3/5
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (!NumberConverter.isNumberic(numberOne) || !NumberConverter.isNumberic(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");

        Double numberOneDouble = NumberConverter.convertToDouble(numberOne);
        Double numberTwoDouble = NumberConverter.convertToDouble(numberTwo);

        if (numberOneDouble == 0 || numberTwoDouble == 0)
            throw new UnsupportedMathOperationException("It is not possible to divide by zero");

        return simpleMath.division(numberOneDouble, numberTwoDouble);
    }

    // http://localhost:8080/math/mean/3/5
    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (!NumberConverter.isNumberic(numberOne) || !NumberConverter.isNumberic(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");

        return simpleMath.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    // http://localhost:8080/math/squareRoot/81
    @RequestMapping("/squareRoot/{number}")
    public Double squareRoot(
            @PathVariable("number") String number
    ) {
        if (!NumberConverter.isNumberic(number)) throw new UnsupportedMathOperationException("Please set a numeric value");

        return Math.sqrt(NumberConverter.convertToDouble(number));
    }

}
