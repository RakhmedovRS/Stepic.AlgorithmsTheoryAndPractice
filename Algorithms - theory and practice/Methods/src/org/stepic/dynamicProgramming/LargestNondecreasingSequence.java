package org.stepic.dynamicProgramming;

/**
 * @author rassoll
 * @created 18.06.2018
 * @$Author$
 * @$Revision$
 */
public class LargestNondecreasingSequence
{
	private class Sequence
	{
		int[] numbers;
		int minValue;
		int maxValue;
		int elementsCounter;

		private void fillNumbers()
		{
			for (int i = 0; i < numbers.length; i++)
			{
				numbers[i] = Integer.MIN_VALUE;
			}
		}

		public Sequence(int size)
		{
			numbers = new int[size];
			minValue = 0;
			maxValue = 0;
			elementsCounter = 0;
			fillNumbers();
		}

		public Sequence(int[] numbers)
		{
			this.numbers = numbers;
			minValue = numbers[0];
			for (int i = 0; i < numbers.length; i++)
			{
				if (numbers[i] != Integer.MIN_VALUE)
				{
					maxValue = numbers[i];
				}
				else
				{
					break;
				}
			}
		}

		public int[] getNumbers()
		{
			return numbers;
		}

		public int getMinValue()
		{
			return minValue;
		}

		public int getMaxValue()
		{
			return maxValue;
		}

		public void insert(int value)
		{
			numbers[elementsCounter++] = value;

			if (value < minValue)
			{
				minValue = value;
			}

			if (value > maxValue)
			{
				maxValue = value;
			}
		}
	}

}
