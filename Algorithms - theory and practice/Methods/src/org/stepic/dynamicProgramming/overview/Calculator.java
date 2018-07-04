package org.stepic.dynamicProgramming.overview;

import java.util.Scanner;

/**
 * У вас есть примитивный калькулятор, который умеет выполнять всего три операции с текущим числом x:
 * заменить x на 2x, 3x или x+1. По данному целому числу 1≤n≤10^5 определите минимальное число операций k,
 * необходимое, чтобы получить n из 1. Выведите k и последовательность промежуточных чисел.
 *
 * @author rassoll
 * @created 04.07.2018
 * @$Author$
 * @$Revision$
 */
public class Calculator
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
		results[results.length - 1] = value;

		int minusIndex;
		int divideBy2Index;
		int divideBy3Index;
		for (int i = results.length - 2; i >= 0; i--)
		{
			if (results[i + 1] % 3 == 0 && results[i + 1] % 2 == 0)
			{
				minusIndex = results[i + 1] - 1;
				divideBy2Index = results[i + 1] / 2;
				divideBy3Index = results[i + 1] / 3;

				if (table[divideBy2Index] > table[divideBy3Index])
				{
					if (table[divideBy2Index] == table[divideBy3Index])
					{
						results[i] = Math.min(divideBy2Index, divideBy3Index);
					}
					else
					{
						results[i] = table[divideBy2Index] > table[divideBy3Index] ? divideBy3Index : divideBy2Index;
					}
				}
				else
				{
					if (table[minusIndex] == table[divideBy3Index])
					{
						results[i] = Math.min(minusIndex, divideBy3Index);
					}
					else
					{
						results[i] = table[minusIndex] > table[divideBy3Index] ? divideBy3Index : minusIndex;
					}
				}
			}
			else if (results[i + 1] % 3 == 0)
			{

				minusIndex = results[i + 1] - 1;
				divideBy3Index = results[i + 1] / 3;
				results[i] = table[divideBy3Index] > table[minusIndex] ? minusIndex : divideBy3Index;
			}
			else if (results[i + 1] % 2 == 0)
			{
				minusIndex = results[i + 1] - 1;
				divideBy2Index = results[i + 1] / 2;
				results[i] = table[divideBy2Index] > table[minusIndex] ? minusIndex : divideBy2Index;
			}
			else
			{
				minusIndex = results[i + 1] - 1;
				results[i] = minusIndex;
			}
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (int result : results)
		{
			stringBuilder.append(result);
			stringBuilder.append(" ");
		}
		System.out.println(results.length - 1);
		System.out.println(stringBuilder.toString());
	}

	public static void main(String[] args)
	{
		int value = new Scanner(System.in).nextInt();
		printResult(getFilledTable(value), value);
	}
}
