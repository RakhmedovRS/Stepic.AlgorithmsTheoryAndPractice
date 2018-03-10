package org.stepic.divideAndRule;

import java.util.Scanner;

/**
 * Первая строка содержит число 1≤n≤10^5, вторая — массив A[1…n], содержащий натуральные числа, не превосходящие 10^9.
 * Необходимо посчитать число пар индексов 1≤i<j≤n, для которых A[i]>A[j].
 * (Такая пара элементов называется инверсией массива.Количество инверсий в массиве является в некотором смысле его мерой
 * неупорядоченности: например, в упорядоченном по неубыванию массиве инверсий нет вообще,
 * а в массиве, упорядоченном по убыванию, инверсию образуют каждые два элемента.)
 *
 * @author rassoll
 * @created 09.03.2018
 * @$Author$
 * @$Revision$
 */
public class NumberOfInversions
{
	private static long inversions = 0;

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int[] array = new int[scanner.nextInt()];

		for (int i = 0; i < array.length; i++)
		{
			array[i] = scanner.nextInt();
		}

		mergeSort(array, 0, array.length - 1);

		System.out.println(inversions);
	}

	private static int[] mergeSort(int[] array, int leftSide, int rightSide)
	{
		int center = (leftSide + rightSide) / 2;

		if (leftSide >= rightSide)
		{
			return new int[]{array[leftSide]};
		}

		if (rightSide - leftSide == 1)
		{
			if (array[leftSide] > array[rightSide])
			{
				inversions++;
				return new int[] {array[rightSide], array[leftSide]};
			}
			return new int[] {array[leftSide], array[rightSide]};
		}

		return merge(mergeSort(array, leftSide, center), mergeSort(array, center + 1, rightSide));
	}

	private static int[] merge(int[] leftArray, int[] rightArray)
	{
		int[] sortedArray = new int[leftArray.length + rightArray.length];

		int leftCounter = 0;
		int rightCounter = 0;
		int sortedCounter = 0;

		for (int i = 0; i < leftArray.length + rightArray.length; i++)
		{
			if (leftCounter == leftArray.length)
			{
				sortedArray[sortedCounter++] = rightArray[rightCounter++];
				continue;
			}

			if (rightCounter == rightArray.length)
			{
				sortedArray[sortedCounter++] = leftArray[leftCounter++];
				continue;
			}

			if (leftArray[leftCounter] > rightArray[rightCounter])
			{
				inversions += leftArray.length - leftCounter;
				sortedArray[sortedCounter++] = rightArray[rightCounter++];
			}
			else
			{
				sortedArray[sortedCounter++] = leftArray[leftCounter++];
			}
		}

		return sortedArray;
	}
}
