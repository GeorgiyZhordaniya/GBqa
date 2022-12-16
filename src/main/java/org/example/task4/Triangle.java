package org.example.task4;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.lang.Math.sqrt;

public class Triangle {

    public double triangleS(int a, int b, int c) throws MyException {

        if (a <= 0 || b <= 0 || c <= 0) {
            throw new MyException("Negative values");
        }
        double result;
        double p = (a + b + c) / 2;
        result = sqrt(p*(p-a)*(p-b)*(p-c));
        BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_DOWN);
        return bd.doubleValue();
    }
}
