package org.stepic.introduction.fibonacciNumbers.smallFibonacci.first;

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
    //[Experimental feedback] Your code complexity score is 5.1 (less is better).
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
            int[] fibArray = new int[elementNumber];
            fibArray[0] = 0;
            fibArray[1] = 1;

            if (elementNumber > 1 && elementNumber <= 40)
            {
                for (int i = 2; i < elementNumber; i++)
                {
                    fibArray[i] = fibArray[i-1] + fibArray[i-2];
                }
            }

            System.out.println(fibArray[fibArray.length - 1] + fibArray[fibArray.length - 2]);
        }
    }
}
