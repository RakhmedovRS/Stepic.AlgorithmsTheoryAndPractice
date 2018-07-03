package org.stepic.introduction.fibonacciNumbers.hugeFibonacci;

import java.util.Scanner;

/**
 * Даны целые числа 1≤n≤10^18 1≤n≤10^18 и 2≤m≤10^5 2≤m≤10^5, необходимо найти остаток от деления nn-го числа Фибоначчи на mm.
 *
 * @author rassoll
 * @created 19.09.2017
 * @$Author$
 * @$Revision$
 */
public class HugeFibonacciNumber
{
    //[Experimental feedback] Your code complexity score is 26.91 (less is better).
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        Long elementNumber = s.nextLong();
        Long divider = s.nextLong();

        System.out.println(getHugeFibonacci(elementNumber, divider));
    }

    /**
     * Метод для определения элемента в последовательности периодов Пизано
     *
     * @param divider делитель
     * @return элемента последовательности периода Пизано
     */
    private static Long calcPisanoPeriods(Long divider)
    {
        Long a = 0L;
        Long b = 1L;
        Long c;

        for (Long i = 0L; i < divider * divider; i++)
        {
            c = (a + b) % divider;
            a = b;
            b = c;

            if (a == 0 && b == 1)
            {
                return i + 1;
            }
        }
        return a;
    }

    /**
     * Метод вычисляющий остаток от деления elementNumber-го числа Фибоначчи на divider
     *
     * @param elementNumber номер числа в последовательности Фибоначчи
     * @param divider делитель
     * @return остаток от деления elementNumber-го числа Фибоначчи на divider
     */
    private static Long getHugeFibonacci(Long elementNumber, Long divider)
    {
        Long remainder = elementNumber % calcPisanoPeriods(divider);

        Long first = 0L;
        Long second = 1L;

        Long result = remainder;

        for (int i = 1; i < remainder; i++)
        {
            result = (first + second) %  divider;
            first = second;
            second = result;
        }

        return result % divider;
    }
}
