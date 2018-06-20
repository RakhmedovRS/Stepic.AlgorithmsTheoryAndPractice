package org.stepic.dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author rassoll
 * @created 18.06.2018
 * @$Author$
 * @$Revision$
 */
public class Main
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		int elementsNumber = scanner.nextInt();
		int[] input = new int[elementsNumber];
		int[] result = new int[elementsNumber];
		for (int i = 0; i < elementsNumber; i++)
		{
			input[i] = scanner.nextInt();
			if (i == 0)
			{
				result[i] = Integer.MIN_VALUE;
			}
			else
			{
				result[i] = Integer.MAX_VALUE;
			}
		}

		for (int i = 0; i < elementsNumber; i++)
		{
			for (int j = 1; j <= elementsNumber; j++)
			{
				if (result[j - 1] < input[i] && input[i] < result[j])
				{
					result[j] = input[i];
				}
			}
		}

		System.out.println(Arrays.toString(result));
	}
}
