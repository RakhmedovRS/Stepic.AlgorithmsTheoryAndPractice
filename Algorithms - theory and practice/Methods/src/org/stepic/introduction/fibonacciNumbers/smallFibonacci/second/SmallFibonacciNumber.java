package org.stepic.introduction.fibonacciNumbers.smallFibonacci.second;

import java.util.Scanner;

/**
 * Дано целое число 1≤n≤401≤n≤40, необходимо вычислить nn-е число Фибоначчи (напомним, что F0=0F0=0, F1=1F1=1 и Fn=Fn−1+Fn−2Fn=Fn−1+Fn−2 при n≥2n≥2).
 *
 * @author rassoll
 * @created 19.09.2017
 * @$Author$
 * @$Revision$
 */
public class SmallFibonacciNumber
{
    //[Experimental feedback] Your code complexity score is 13.93 (less is better).
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
            if (elementNumber > 1 && elementNumber <= 40)
            {
                int nMunisTwoElement = 0;
                int nMunisOneElement = 1;
                int tempValue;
                for (int i = 2; i < elementNumber; i++)
                {
                    tempValue = nMunisOneElement + nMunisTwoElement;
                    nMunisTwoElement = nMunisOneElement;
                    nMunisOneElement = tempValue;
                }
                System.out.println(nMunisOneElement + nMunisTwoElement);
            }
        }
    }
}
