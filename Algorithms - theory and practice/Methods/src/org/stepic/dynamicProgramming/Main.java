package org.stepic.dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class Main
{
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

	private static int getPosition(int value)
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

	private static void getLIS()
	{
		for (int i = 0; i < elementsNumber; i++)
		{
			int position = getPosition(inputValues[i]);

			if (tail[position - 1] <= inputValues[i] && inputValues[i] < tail[position])
			{
				tail[position] = inputValues[i];
				pos[position] = i;
				prev[i] = pos[position - 1];

				lisSize = position > lisSize ? position : lisSize;
			}
		}
	}

	public static void main(String[] args)
	{
		fillArrays();
		getLIS();

		StringBuilder stringBuilder = new StringBuilder();

		int p = pos[lisSize];
		while (true)
		{
			if (p == -1)
			{
				break;
			}

			stringBuilder.append(elementsNumber - p);
			stringBuilder.append(" ");
			p = prev[p];
		}

		System.out.println(lisSize);
		System.out.println(stringBuilder.toString());
	}
}

