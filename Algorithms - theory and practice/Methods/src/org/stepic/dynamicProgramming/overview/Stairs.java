package org.stepic.dynamicProgramming.overview;

import java.util.Scanner;

/**
 * Даны число 1≤n≤10^2 ступенек лестницы и целые числа −10^4≤a 1,…,an≤10^4, которыми помечены ступеньки.
 * Найдите максимальную сумму, которую можно получить, идя по лестнице снизу вверх (от нулевой до n-й ступеньки),
 * каждый раз поднимаясь на одну или две ступеньки.
 *
 * @author rassoll
 * @created 03.07.2018
 * @$Author$
 * @$Revision$
 */
public class Stairs
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int[] stairs = new int[scanner.nextInt()];
		int[] result = new int[stairs.length + 1];
		for (int i = 0; i < stairs.length; i++)
		{
			stairs[i] = scanner.nextInt();
		}

		if (stairs.length == 0)
		{
			System.out.println(0);
		}
		else if (stairs.length == 1)
		{
			System.out.println(stairs[0]);
		}
		else
		{
			result[0] = 0;
			result[1] = stairs[0];
			for (int i = 1; i < stairs.length; i++)
			{
				int first = result[i - 1] + stairs[i];
				int second = result[i] + stairs[i];
				result[i + 1] = Math.max(first, second);
			}
			System.out.println(result[stairs.length]);
		}
	}
}
