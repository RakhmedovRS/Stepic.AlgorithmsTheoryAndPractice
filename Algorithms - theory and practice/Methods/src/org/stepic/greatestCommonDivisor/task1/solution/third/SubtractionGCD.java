package org.stepic.greatestCommonDivisor.task1.solution.third;

import java.util.Scanner;

/**
 * По данным двум числам 1≤a,b≤2⋅1091≤a,b≤2⋅109 найдите их наибольший общий делитель.
 *
 * @author rassoll
 * @created 21.09.2017
 * @$Author$
 * @$Revision$
 */
public class SubtractionGCD
{

    //[Experimental feedback] Your code complexity score is 21.54 (less is better).
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int firstValue = s.nextInt();
        int secondValue = s.nextInt();

        System.out.println(getGCD(firstValue, secondValue));
    }

    /**
     * Метод поиска наибольшего общего делителя числе firstValue и secondValue
     *
     * @param firstValue первое число
     * @param secondValue второе число
     * @return наибольший общий делитель
     */
    private static int getGCD(int firstValue, int secondValue)
    {
        int result = 0;

        if (firstValue == 0)
        {
            return result;
        } else if (secondValue == 0)
        {
            return result;
        }

        while (firstValue != 0 && secondValue != 0)
        {
            if (firstValue > secondValue)
            {
                if (firstValue - secondValue == 0)
                {
                    result = secondValue;
                    break;
                } else
                {
                    firstValue = firstValue - secondValue;
                }
            }
            else
            {
                if (secondValue - firstValue == 0)
                {
                    result = firstValue;
                    break;
                }
                else
                {
                    secondValue = secondValue - firstValue;
                }
            }
        }

        return result;
    }
}
