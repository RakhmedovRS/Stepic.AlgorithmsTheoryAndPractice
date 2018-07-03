package org.stepic.introduction.fibonacciNumbers.bigFibonacci.first;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Дано число 1≤n≤10^7 1≤n≤10^7, необходимо найти последнюю цифру nn-го числа Фибоначчи.
 * Как мы помним, числа Фибоначчи растут очень быстро, поэтому при их вычислении нужно быть аккуратным с переполнением.
 * В данной задаче, впрочем, этой проблемы можно избежать, поскольку нас интересует только последняя цифра числа Фибоначчи:
 * если 0≤a,b≤9 0≤a,b≤9 — последние цифры чисел FiFi и Fi+1Fi+1 соответственно, то (a+b)mod10(a+b)mod10 — последняя цифра числа Fi+2Fi+2.
 *
 * @author rassoll
 * @created 19.09.2017
 * @$Author$
 * @$Revision$
 */
public class BigFibonacciNumber
{
    //[Experimental feedback] Your code complexity score is 30.61 (less is better).
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int elementNumber = s.nextInt();

        BigInteger[][] initMatrix = new BigInteger[2][2];
        initMatrix[0][0] = BigInteger.ZERO;
        initMatrix[1][0] = BigInteger.ONE;
        initMatrix[0][1] = BigInteger.ONE;
        initMatrix[1][1] = BigInteger.ONE;

        System.out.println(getBigFibonacci(initMatrix, elementNumber).mod(BigInteger.valueOf(10L)));
    }

    /**
     * Метод выполняющий возведение матрицы в степень
     *
     * @param initMatrix матрица
     * @param pow степень
     * @return матрица возведенная в степень
     */
    private static BigInteger getBigFibonacci(final BigInteger[][] initMatrix, int pow)
    {
        BigInteger[][] firstMatrix = initMatrix.clone();
        BigInteger[][] secondMatrix = initMatrix.clone();

        while (pow != 0)
        {
            if (pow%2 == 1)
            {
                firstMatrix = multiplyMatrix(firstMatrix, secondMatrix);
                --pow;
            }
            else
            {
                secondMatrix = multiplyMatrix(secondMatrix, secondMatrix);
                pow /= 2;
            }
        }
        return firstMatrix[0][0];
    }

    /**
     * Метод для умножения одной матрицы на другую
     *
     * @param firstMatrix первая матрица множитель
     * @param secondMatrix вторая матрица множитель
     * @return произведение переданных матриц
     */
    private static BigInteger[][] multiplyMatrix(BigInteger[][] firstMatrix, BigInteger[][] secondMatrix)
    {
        return new BigInteger[][]
                {
                        {
                                firstMatrix[0][0].multiply(secondMatrix[0][0]).add(firstMatrix[0][1].multiply(secondMatrix[1][0])),
                                firstMatrix[1][0].multiply(secondMatrix[0][0]).add(firstMatrix[1][1].multiply(secondMatrix[1][0]))
                        },
                        {
                                firstMatrix[0][0].multiply(secondMatrix[0][1]).add(firstMatrix[0][1].multiply(secondMatrix[1][1])),
                                firstMatrix[1][0].multiply(secondMatrix[0][1]).add(firstMatrix[1][1].multiply(secondMatrix[1][1]))
                        }
                };
    }
}
