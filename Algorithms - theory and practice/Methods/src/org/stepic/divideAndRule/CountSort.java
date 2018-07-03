package org.stepic.divideAndRule;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Первая строка содержит число 1≤n≤104, вторая — n натуральных чисел, не превышающих 10.
 * Выведите упорядоченную по неубыванию последовательность этих чисел.
 *
 * @author rassoll
 * @ created 15.05.2018
 * @ $Author$
 * @ $Revision$
 */
public class CountSort
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int[] elements = new int[scanner.nextInt()];
		int maxElement = 0;
		for (int i = 0; i < elements.length; i++)
		{
			elements[i] = scanner.nextInt();
			if (elements[i] > maxElement)
			{
				maxElement = elements[i];
			}
		}

		System.out.println(Arrays.toString(sort(elements, maxElement)).replaceAll("\\[", "").replaceAll("]", "").replaceAll(",", ""));
	}

	private static int[] sort(int[] elements, int maxElement)
	{
		int[] temp = new int[maxElement + 1];
		int[] result = new int[elements.length];

		for (int element : elements)
		{
			temp[element] = temp[element] + 1;
		}

		for (int i = 2; i < temp.length; i++)
		{
			temp[i] = temp[i] + temp[i - 1];
		}

		for (int i = elements.length - 1; i >= 0; i--)
		{
			result[temp[elements[i]] - 1] = elements[i];
			temp[elements[i]] = temp[elements[i]] - 1;
		}

		return result;
	}
}
