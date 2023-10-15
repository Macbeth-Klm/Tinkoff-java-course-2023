package edu.hw2.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    void checkResult() {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));

        double result = res.evaluate();

        assertThat(result)
            .isEqualTo(37);
    }

    @Test
    void nullValueArgumentsTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var four = new Expr.Constant(4);
            var sum = new Expr.Addition(null, four);
        });
    }
}
