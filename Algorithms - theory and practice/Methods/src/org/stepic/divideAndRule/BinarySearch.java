package org.stepic.divideAndRule;

import java.util.Scanner;

/**
 * В первой строке даны целое число 1≤n≤10^5 и массив A[1…n] из n различных натуральных чисел, не превышающих 10^9,
 * в порядке возрастания, во второй — целое число 1≤k≤10^5 и k натуральных чисел b1,…,bk, не превышающих 10^9.
 * Для каждого i от 1 до k необходимо вывести индекс 1≤j≤n, для которого A[j]=bi, или −1, если такого j нет.
 *
 * @author rassoll
 * @created 07.03.2018
 * @$Author$
 * @$Revision$
 */
public class BinarySearch
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int[] firstArray = new int[scanner.nextInt()];
		for (int i = 0; i < firstArray.length; i++)
		{
			firstArray[i] = scanner.nextInt();
		}

		int elementsCount = scanner.nextInt();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < elementsCount; i++)
		{
			if (i != 0)
			{
				builder.append(" ");
			}
			builder.append(binarySearch(firstArray, scanner.nextInt()));
		}

		System.out.println(builder.toString());
	}

	private static int binarySearch(int[] firstArray, int searchValue)
	{
		int retValue = -1;

		int leftSize = 0;
		int rightSize = firstArray.length - 1;
		int center;

		while (true)
		{
			center = (leftSize + rightSize) / 2;

			if (firstArray[center] == searchValue)
			{
				retValue = center + 1;
				break;
			}
			else if (firstArray[center] < searchValue)
			{
				leftSize = center + 1;
			}
			else if (firstArray[center] > searchValue)
			{
				rightSize = center - 1;
			}

			if (leftSize > rightSize)
			{
				break;
			}
		}

		return retValue;
	}
}
