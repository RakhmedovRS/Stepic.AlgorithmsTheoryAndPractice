package org.stepic.greedyAlgorithms.intro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * По данному числу 1≤n≤10^9 1≤n≤10^9 найдите максимальное число kk,
 * для которого nn можно представить как сумму kk различных натуральных слагаемых.
 * Выведите в первой строке число kk, во второй — kk слагаемых.
 *
 * @author rassoll
 * @created 28.09.2017
 * @$Author$
 * @$Revision$
 */
public class DifferentAddend
{
    //[Experimental feedback] Your code complexity score is 26.65 (less is better).
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int dividend = scan.nextInt();

        List<Integer> result = new ArrayList<>();

        int i = 1;
        int counter = 0;
        int elementsSum = 0;
        while (true)
        {
            if (dividend <= 2)
            {
                result.add(dividend);
                break;
            }

            if (elementsSum + i <= dividend)
            {
                result.add(i);
                elementsSum += i;
                i++;
                counter++;
            }
            else
            {
                counter --;
                while (true)
                {
                    if (dividend == elementsSum)
                    {
                        break;
                    }

                    result.set(counter == 0 ? 0: counter, result.get(counter) + 1);
                    elementsSum += 1;
                }
                break;
            }
        }

        System.out.println(result.size());
        System.out.print(result.get(0));

        for (int j = 1; j < result.size(); j++)
        {
            System.out.print(" " + result.get(j));
        }
    }
}
