package org.stepic.dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Дано целое число 1≤n≤105и массив A[1…n], содержащий неотрицательные целые числа, не превосходящие 10^9.
 * Найдите наибольшую невозрастающую подпоследовательность в A. В первой строке выведите её длину k,
 * во второй — её индексы 1≤i1<i2<…<ik≤n (таким образом, A[i1]≥A[i2]≥…≥A[in]).
 *
 * @author rassoll
 * @created 09.03.2018
 * @$Author$
 * @$Revision$
 */
public class LargestNonIncreasingSubSequence
{
	/** Флаги настроек */
	private static final boolean useBinarySearch = true;
	private static final boolean printValues = false;

	private static int elementsNumber;
	private static int lisSize = 0;
	private static int[] inputValues;
	private static int[] tail;
	private static int[] pos;
	private static int[] prev;

	private static void fillArrays()
	{
		Scanner scanner = new Scanner(System.in);

		elementsNumber = scanner.nextInt();
		inputValues = new int[elementsNumber];

		for (int i = elementsNumber - 1; i >= 0; i--)
		{
			inputValues[i] = scanner.nextInt();
		}
		scanner.close();

		tail = new int[elementsNumber + 1];
		Arrays.fill(tail, Integer.MAX_VALUE);
		tail[0] = Integer.MIN_VALUE;

		pos = new int[inputValues.length + 1];
		Arrays.fill(pos, 0);
		pos[0] = -1;

		prev = new int[inputValues.length + 1];
		Arrays.fill(prev, -1);
	}

	private static int LinearSearch(int value)
	{
		int i;
		for (i = 0; i < tail.length; i++)
		{
			if (value < tail[i])
			{
				break;
			}
		}

		return i;
	}

	private static int BinarySearch(int value)
	{
		int left = 0;
		int right = lisSize + 1;
		int middle = 0;

		while (left < right)
		{
			middle = (left + right) / 2 + 1;

			if (middle - 1 >= 0
				&& tail[middle - 1] <= value
				&& tail[middle] > value)
			{
				return middle;
			}

			if (tail[middle] > value)
			{
				right = middle - 1;
			}
			else
			{
				left = middle + 1;
			}
		}

		if (tail[right] > value)
		{
			return right;
		}
		else if (tail[left] > value)
		{
			return left;
		}
		else
		{
			return middle;
		}
	}

	private static void findLIS()
	{
		for (int i = 0; i < elementsNumber; i++)
		{
			int position = useBinarySearch ? BinarySearch(inputValues[i]) : LinearSearch(inputValues[i]);

			if (tail[position - 1] <= inputValues[i] && inputValues[i] < tail[position])
			{
				tail[position] = inputValues[i];
				pos[position] = i;
				prev[i] = pos[position - 1];

				lisSize = position > lisSize ? position : lisSize;
			}
		}
	}

	private static void printResults()
	{
		StringBuilder stringBuilder = new StringBuilder();

		int position = pos[lisSize];
		while (position != -1)
		{
			stringBuilder.append(printValues ? inputValues[position] : elementsNumber - position);
			stringBuilder.append(" ");
			position = prev[position];
		}

		System.out.println(lisSize);
		System.out.println(stringBuilder.toString());
	}

	public static void main(String[] args)
	{
		fillArrays();
		findLIS();
		printResults();
	}
}

