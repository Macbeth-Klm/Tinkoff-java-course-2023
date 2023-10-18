package edu.hw2.task1;

import org.jetbrains.annotations.NotNull;

public sealed interface Expr {
    double evaluate();

    record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }
    }

    record Negate(@NotNull Constant value) implements Expr {
        @Override
        public double evaluate() {
            return value.evaluate() * (-1);
        }
    }

    record Addition(@NotNull Expr firstTerm, @NotNull Expr secondTerm) implements Expr {
        @Override
        public double evaluate() {
            return firstTerm.evaluate() + secondTerm.evaluate();
        }
    }

    record Multiplication(@NotNull Expr firstMultiplier, @NotNull Expr secondMultiplier) implements Expr {
        @Override
        public double evaluate() {
            return firstMultiplier.evaluate() * secondMultiplier.evaluate();
        }
    }

    record Exponent(@NotNull Expr basis, double degree) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(basis.evaluate(), degree);
        }
    }
}
