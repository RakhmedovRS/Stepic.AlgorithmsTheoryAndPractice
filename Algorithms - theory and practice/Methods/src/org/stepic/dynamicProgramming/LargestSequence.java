package org.stepic.dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author rassoll
 * @created 18.06.2018
 * @$Author$
 * @$Revision$
 */
public class LargestSequence
{
	private static int valuesCount;
	private static int[] values;

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		valuesCount = scanner.nextInt();
		values = new int[valuesCount];

		for (int i = 0; i < values.length; i++)
		{
			values[i] = scanner.nextInt();
		}

		Sequence[] sequences = new Sequence[valuesCount];
		int sequencesCounter = 0;
		sequences[sequencesCounter++] = new Sequence(valuesCount).add(0);
		int minValue = values[0];
		int maxValue = values[0];
		for (int i = 1; i < valuesCount; i++)
		{
			if (values[i] < minValue)
			{
				sequences[i] = new Sequence(valuesCount).add(i);
				minValue = values[i];
			}
			else if (values[i] >= maxValue)
			{
				Sequence newSequence = new Sequence(getLargestSequence(sequences).getNumbers());
				newSequence.add(i);
				sequences[sequencesCounter++] = newSequence;
				maxValue = values[i];
			}
			else
			{
				Sequence lessMaxValue = sequences[0];

				for (int j = 1; j < sequencesCounter; j++)
				{
					if (sequences[j] == null)
					{
						break;
					}

					if (sequences[j].getMaxValue() > lessMaxValue.getMaxValue()
						&& sequences[j].getMaxValue() < maxValue
						&& values[i] > sequences[j].getMaxValue())
					{
						lessMaxValue = sequences[j];
					}
				}

				Sequence newSequence = new Sequence(lessMaxValue.getNumbers());
				newSequence.add(i);

				for (int k = 0; k < valuesCount; k++)
				{
					if (sequences[k].getElementsCount() == newSequence.getElementsCount())
					{
						sequences[k] = newSequence;
						break;
					}
				}

				maxValue = Integer.MIN_VALUE;
				for (Sequence sequence : sequences)
				{
					if (sequence == null)
					{
						break;
					}
					else
					{
						if (sequence.getMaxValue() > maxValue)
						{
							maxValue = sequence.getMaxValue();
						}
					}
				}
			}
		}

		outputResult(sequences);
	}

	private static Sequence getLargestSequence(Sequence[] sequences)
	{
		Sequence largestSequence = sequences[0];
		for (Sequence sequence : sequences)
		{
			if (sequence == null)
			{
				break;
			}

			if (sequence.getElementsCount() > largestSequence.getElementsCount())
			{
				largestSequence = sequence;
			}
		}

		return largestSequence;
	}

	private static void outputResult(Sequence[] sequences)
	{
		Sequence largestSequence = sequences[0];
		for (Sequence sequence : sequences)
		{
			if (sequence == null)
			{
				break;
			}
			else
			{
				if (sequence.getElementsCount() > largestSequence.getElementsCount())
				{
					largestSequence = sequence;
				}
			}
		}

		System.out.println(largestSequence.getElementsCount() + 1);
		StringBuilder stringBuilder = new StringBuilder();
		for (int index : largestSequence.getNumbers())
		{
			if (index == Integer.MIN_VALUE)
			{
				break;
			}
			else
			{
				stringBuilder.append(index);
				stringBuilder.append(" ");
			}
		}
		System.out.println(stringBuilder.toString());
	}

	private static class Sequence
	{
		private int[] numbers;
		private int minValue;
		private int maxValue;
		private int elementsCounter;

		Sequence(int size)
		{
			numbers = new int[size];
			minValue = Integer.MAX_VALUE;
			maxValue = Integer.MIN_VALUE;
			elementsCounter = 0;
			fillNumbers();
		}

		Sequence(int[] numbers)
		{
			this.numbers = Arrays.copyOf(numbers, numbers.length);
			minValue = numbers[0];
			for (int number : numbers)
			{
				if (number != Integer.MIN_VALUE)
				{
					maxValue = number;
					elementsCounter++;
				}
				else
				{
					break;
				}
			}
		}

		int getElementsCount()
		{
			return elementsCounter;
		}

		int[] getNumbers()
		{
			return numbers;
		}

		int getMinValue()
		{
			return minValue;
		}

		int getMaxValue()
		{
			return maxValue;
		}

		Sequence add(int index)
		{
			numbers[elementsCounter] = index;

			if (values[index] < minValue)
			{
				minValue = values[index];
			}

			if (values[index] > maxValue)
			{
				maxValue = values[index];
			}

			return this;
		}

		private void fillNumbers()
		{
			for (int i = 0; i < numbers.length; i++)
			{
				numbers[i] = Integer.MIN_VALUE;
			}
		}
	}
}
