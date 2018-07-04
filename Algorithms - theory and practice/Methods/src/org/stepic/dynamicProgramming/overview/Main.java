package org.stepic.dynamicProgramming.overview;

import java.util.Scanner;

public class Main
{
	private static int[] getFilledTable(int value)
	{
		int[] results = new int[value + 1];
		results[0] = 0;
		results[1] = 1;

		for (int i = 2; i < results.length; i++)
		{
			if (i % 3 == 0 && i % 2 == 0)
			{
				results[i] = 1 + Math.min(Math.min(results[i / 3], results[i / 2]), results[i - 1]);
			}
			else if (i % 3 == 0)
			{
				results[i] = 1 + Math.min(results[i / 3], results[i - 1]);
			}
			else if (i % 2 == 0)
			{
				results[i] = 1 + Math.min(results[i / 2], results[i - 1]);
			}
			else
			{
				results[i] = 1 + results[i - 1];
			}
		}

		return results;
	}

	private static void printResult(int[] table, int value)
	{
		int[] results = new int[table[value]];

		for (int i = results.length -1; i>= 0; i--)
		{
		}
	}

	public static void main(String[] args)
	{
		int value = new Scanner(System.in).nextInt();
		printResult(getFilledTable(value), value);
	}
}
