package org.stepic.dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class Main2
{
	private static int[] inputValues;

	static
	{
		Scanner scanner = new Scanner(System.in);

		int elementsNumber = scanner.nextInt();
		inputValues = new int[elementsNumber];

		for (int i = elementsNumber - 1; i >= 0; i--)
		{
			inputValues[i] = scanner.nextInt();
		}

		scanner.close();
	}

	private static int getPosition(int[] tail, int left, int right, int value)
	{
		while (right - left > 1)
		{
			int middle = left + (right - 1) / 2;
			if (inputValues[tail[middle]] >= value)
			{
				right = middle;
			}
			else
			{
				left = middle;
			}
		}

		return right;
	}

	public static void main(String[] args)
	{
		int[] tail = new int[inputValues.length];
		Arrays.fill(tail, 0);

		int[] prev = new int[inputValues.length];
		Arrays.fill(prev, -1);

		int length = 1;

		for (int i = 1; i < inputValues.length; i++)
		{
			//Наименьший элемент
			if (inputValues[i] < inputValues[tail[0]])
			{
				tail[0] = inputValues[i];
			}
			//Наибольший элемент
			else if (inputValues[i] > inputValues[tail[length - 1]])
			{
				prev[i] = tail[length - 1];
				tail[length++] = i;
			}
			else
			{
				int position = getPosition(tail, -1, length - 1, inputValues[i]);
				prev[i] = tail[position - 1];
				tail[position] = i;
			}
		}
	}
}
