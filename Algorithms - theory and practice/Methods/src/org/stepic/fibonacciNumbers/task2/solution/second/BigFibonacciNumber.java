package org.stepic.fibonacciNumbers.task2.solution.second;

import java.util.Scanner;

/**
 * Дано число 1≤n≤1071≤n≤107, необходимо найти последнюю цифру nn-го числа Фибоначчи.
 * Как мы помним, числа Фибоначчи растут очень быстро, поэтому при их вычислении нужно быть аккуратным с переполнением.
 * В данной задаче, впрочем, этой проблемы можно избежать, поскольку нас интересует только последняя цифра числа Фибоначчи:
 * если 0≤a,b≤90≤a,b≤9 — последние цифры чисел FiFi и Fi+1Fi+1 соответственно, то (a+b)mod10(a+b)mod10 — последняя цифра числа Fi+2Fi+2.
 *
 * @author rassoll
 * @created 21.09.2017
 * @$Author$
 * @$Revision$
 */
public class BigFibonacciNumber
{
    //[Experimental feedback] Your code complexity score is 26.48 (less is better).
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int elementNumber = s.nextInt();

        int[][] initMatrix = new int[2][2];
        initMatrix[0][0] = 0;
        initMatrix[1][0] = 1;
        initMatrix[0][1] = 1;
        initMatrix[1][1] = 1;

        System.out.println(getBigFibonacci(initMatrix, elementNumber));
    }

    /**
     * Метод выполняющий возведение матрицы в степень
     *
     * @param initMatrix матрица
     * @param pow степень
     * @return матрица возведенная в степень
     */
    private static int getBigFibonacci(int[][] initMatrix, int pow)
    {
        int[][] firstMatrix = initMatrix.clone();
        int[][] secondMatrix = initMatrix.clone();

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
     * @return произведение переданных матриц по модулю 10
     */
    private static int[][] multiplyMatrix(int[][] firstMatrix, int[][] secondMatrix)
    {
        return new int[][]
                {
                        {
                                (firstMatrix[0][0] * secondMatrix[0][0] + firstMatrix[0][1] * secondMatrix[1][0]) % 10,
                                (firstMatrix[1][0] * secondMatrix[0][0] + firstMatrix[1][1] * secondMatrix[1][0]) % 10
                        },
                        {
                                (firstMatrix[0][0] * secondMatrix[0][1] + firstMatrix[0][1] * secondMatrix[1][1]) % 10,
                                (firstMatrix[1][0] * secondMatrix[0][1] + firstMatrix[1][1] * secondMatrix[1][1]) % 10
                        }
                };
    }
}
