package org.stepic.dynamicProgramming;

import java.util.Scanner;

/**
 * Дано целое число 1≤n≤10^3 и массив A[1…n] натуральных чисел, не превосходящих 2⋅10^9. Выведите максимальное 1≤k≤n, для которого найдётся подпоследовательность
 * 1≤i1<i2<…<ik≤n длины k, в которой каждый элемент делится на предыдущий (формально: для  всех 1≤j<k, A[ij]|A[ij+1]).
 *
 * @author rassoll
 * @created 09.03.2018
 * @$Author$
 * @$Revision$
 */
public class LargestSequentialSubSequence
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int[] elements = new int[scanner.nextInt()];

		for (int i = 0; i < elements.length; i++)
		{
			elements[i] = scanner.nextInt();
		}

		System.out.println(getSequenceLength(elements));
	}

	private static int getSequenceLength(int[] elements)
	{
		int[] sequences = new int[elements.length];

		for (int i = 0; i < elements.length; i++)
		{
			sequences[i] = 1;
			for (int j = 0; j <= i - 1; j++)
			{
				if ((elements[i] % elements[j] == 0)
					&& (sequences[j] + 1 > sequences[i]))
				{
					sequences[i] = sequences[j] + 1;
				}
			}
		}

		int maxSequence = 0;
		for (int i = 0; i < elements.length; i++)
		{
			if (sequences[i] > maxSequence)
			{
				maxSequence = sequences[i];
			}
		}

		return maxSequence;
	}
}
