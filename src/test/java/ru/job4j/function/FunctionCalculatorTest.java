package ru.job4j.function;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionCalculatorTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        FunctionCalculator function = new FunctionCalculator();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void whenPolynomialFunctionThenParabolicResults() {
        FunctionCalculator function = new FunctionCalculator();
        List<Double> result = function.diapason(2, 11, x -> 2 * Math.pow(x, 2) + 1);
        List<Double> expected = Arrays.asList(9D, 19D, 33D, 51D, 73D, 99D, 129D, 163D, 201D);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void whenExponentialFunctionThenExponentialResults() {
        FunctionCalculator function = new FunctionCalculator();
        List<Double> result = function.diapason(1, 6, x -> Math.pow(3, x));
        List<Double> expected = Arrays.asList(3D, 9D, 27D, 81D, 243D);
        assertThat(result).containsAll(expected);
    }
}