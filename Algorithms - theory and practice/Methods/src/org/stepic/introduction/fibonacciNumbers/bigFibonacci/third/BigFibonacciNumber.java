package org.stepic.introduction.fibonacciNumbers.bigFibonacci.third;

import java.util.Scanner;

/**
 * Дано число 1≤n≤1071≤n≤107, необходимо найти последнюю цифру nn-го числа Фибоначчи.
 * Как мы помним, числа Фибоначчи растут очень быстро, поэтому при их вычислении нужно быть аккуратным с переполнением.
 * В данной задаче, впрочем, этой проблемы можно избежать, поскольку нас интересует только последняя цифра числа Фибоначчи:
 * если 0≤a,b≤90≤a,b≤9 — последние цифры чисел FiFi и Fi+1Fi+1 соответственно, то (a+b)mod10(a+b)mod10 — последняя цифра числа Fi+2Fi+2.
 *
 * @author rassoll
 * @created 19.09.2017
 * @$Author$
 * @$Revision$
 */
public class BigFibonacciNumber
{
    //[Experimental feedback] Your code complexity score is 12.88 (less is better).
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int elementNumber = s.nextInt();

        if (elementNumber == 1)
        {
            System.out.println(elementNumber);
        }
        else
        {
            int nMunisTwoElement = 0;
            int nMunisOneElement = 1;
            int tempValue;
            for (int i = 2; i < elementNumber; i++)
            {
                tempValue = (nMunisOneElement + nMunisTwoElement) % 10;
                nMunisTwoElement = nMunisOneElement;
                nMunisOneElement = tempValue;
            }
            System.out.println((nMunisOneElement + nMunisTwoElement)%10);
        }
    }
}
