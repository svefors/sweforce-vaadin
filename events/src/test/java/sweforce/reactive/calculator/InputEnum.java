package sweforce.reactive.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum InputEnum {

    zero("0"), one("1"), two("2"), three("3"), four("4"), five("5"), six("6"), seven("7"), eight("8"), nine("9"),
    point("."),
    plus("+") {
        public BigDecimal eval(BigDecimal a, BigDecimal b, int scale) {
            return a.add(b);
        }},
    minus("-") {public BigDecimal eval(BigDecimal a, BigDecimal b, int scale) {
        return a.subtract(b);
    }},
    divide("/") {public BigDecimal eval(BigDecimal a, BigDecimal b, int scale) {
        return a.divide(b, scale, RoundingMode.HALF_UP).stripTrailingZeros();
    }},
    mult("*") {public BigDecimal eval(BigDecimal a, BigDecimal b, int scale) {
        return a.multiply(b);
    }},
    clear("C"), equals("="), done("\0"), noOp("\0") ;

    String c;


    InputEnum(String c) {
        this.c = c;
    }

    public int value() {
        if (!isDigit())
            throw new IllegalStateException();
        return c.charAt(0) - '0';
    }

    public BigDecimal eval(BigDecimal a, BigDecimal b) {
        if (this == divide)
            throw new IllegalStateException();

        return eval(a,b,0);
    }

    public BigDecimal eval(BigDecimal a, BigDecimal b, int scale) {
        throw new IllegalArgumentException();
    }

    public boolean isDigit() {
        return  c.charAt(0) - '0' >= 0 && c.charAt(0) - '0' <= 9;
//                this == zero || this == one || this == two || this == three || this == four || this == five || this == six
//                || this == seven || this == eight || this == nine;
    }

    public String toString()
    {
        return c;
    }


    public static InputEnum map(char c) {
        switch (c) {
            case '1':
                return InputEnum.one;
            case '2':
                return InputEnum.two;
            case '3':
                return InputEnum.three;
            case '4':
                return InputEnum.four;
            case '5':
                return InputEnum.five;
            case '6':
                return InputEnum.six;
            case '7':
                return InputEnum.seven;
            case '8':
                return InputEnum.eight;
            case '9':
                return InputEnum.nine;
            case '+':
                return InputEnum.plus;
            case '-':
                return InputEnum.minus;
            case '/':
                return InputEnum.divide;
            case '*':
                return InputEnum.mult;
            case '=':
                return InputEnum.equals;
            case 'C':
                return InputEnum.clear;
            case '.':
                return InputEnum.point;
            default:
                return InputEnum.done;
        }
    }
}
