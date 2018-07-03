package org.stepic.introduction.fibonacciNumbers.bigFibonacci.five;

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
    private static final int MOD = 10;

    //[Experimental feedback] Your code complexity score is 11.53 (less is better).
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        Integer elementNumber = s.nextInt();

        System.out.println(getBigFibonacci(elementNumber));
    }

    /**
     * Метод вычисляющий последнюю цифру elementNumber-го числа Фибоначчи.
     *
     * @param elementNumber номер элемента
     * @return последняя цифра elementNumber-го числа Фибоначчи.
     */
    private static Integer getBigFibonacci(Integer elementNumber)
    {
        int a = 0;
        int b = 1;

        for (int i = 0; i < elementNumber; i++)
        {
            int c = (a + b) % MOD;
            a = b;
            b = c;
        }

        return a;
    }
}
