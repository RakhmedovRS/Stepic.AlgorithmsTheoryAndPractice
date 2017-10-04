package org.stepic.greatestCommonDivisor.task1.solution.first;

import java.util.Scanner;

/**
 * По данным двум числам 1≤a,b≤2⋅1091≤a,b≤2⋅109 найдите их наибольший общий делитель.
 *
 * @author rassoll
 * @created 21.09.2017
 * @$Author$
 * @$Revision$
 */
public class EuclidGCD
{
    //[Experimental feedback] Your code complexity score is 15.17 (less is better).
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
        if (firstValue == 0)
        {
            return secondValue;
        }
        else if (secondValue == 0)
        {
            return firstValue;
        }
        else if (firstValue >= secondValue)
        {
            return getGCD(firstValue % secondValue, secondValue);
        }
        else
        {
            return getGCD(firstValue, secondValue % firstValue);
        }
    }
}
