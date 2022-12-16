package org.example.task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TriangleTest {

    @Test
    void test() throws MyException {
        Triangle triangle = new Triangle();
        Assertions.assertEquals(6, triangle.triangleS(3,4,5));
        Assertions.assertThrows(MyException.class,() -> triangle.triangleS(-1,3,4));
    }

    @ParameterizedTest
    @CsvSource({ "3, 4, 5, 6", "11, 22, 15, 74.94"})
    void paramTest(int a, int b, int c, double result) throws MyException {
        Triangle triangle = new Triangle();
        Assertions.assertEquals(result, triangle.triangleS(a, b, c));
    }

    @ParameterizedTest
    @CsvSource({ "0, 1, 2", "1, -10, 2"})
    void paramTest(int a, int b, int c) {
        Triangle triangle = new Triangle();
        Assertions.assertThrows(MyException.class, () -> triangle.triangleS(a, b ,c));
    }

}
