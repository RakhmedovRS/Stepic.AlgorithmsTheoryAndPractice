package org.stepic.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Main
{
	private static int[] inputValues;
	private static int minValue = Integer.MAX_VALUE;
	private static int maxValue = Integer.MIN_VALUE;

	private static int[] getInputValues()
	{
		Scanner scanner = new Scanner(System.in);

		int elementsNumber = scanner.nextInt();
		int[] input = new int[elementsNumber];

		for (int i = elementsNumber - 1; i >= 0; i--)
		{
			input[i] = scanner.nextInt();
		}

		maxValue = input[0];
		return input;
	}

	private static void restoreMinAndMax(List<List<Integer>> inputCollection)
	{
		minValue = Integer.MAX_VALUE;
		maxValue = Integer.MIN_VALUE;
		for (List<Integer> list : inputCollection)
		{
			if (list != null)
			{
				int index = list.listIterator(list.size()).previous();
				if (minValue > inputValues[index])
				{
					minValue = inputValues[index];
				}
				if (inputValues[index] > maxValue)
				{
					maxValue = inputValues[index];
				}
			}
		}
	}

	private static List<Integer> getLargestList(List<List<Integer>> inputCollection)
	{
		List<Integer> largestList = null;
		for (List<Integer> list : inputCollection)
		{
			if (largestList == null)
			{
				largestList = list;
			}
			else
			{
				if (list.size() > largestList.size())
				{
					largestList = list;
				}
			}
		}

		return largestList;
	}

	private static List<Integer> getLargestList(List<List<Integer>> inputCollection, int largestValue)
	{
		List<Integer> largestList = null;
		int maxLength = Integer.MIN_VALUE;
		for (List<Integer> list : inputCollection)
		{
			int largestListElement = inputValues[list.listIterator(list.size()).previous()];
			if (largestListElement <= largestValue
				&& list.size() > maxLength)
			{
				largestList = list;
				maxLength = list.size();
			}
		}
		return largestList;
	}

	private static void removeListByLength(List<List<Integer>> inputCollection, int length)
	{
		ListIterator<List<Integer>> listIterator = inputCollection.listIterator();

		while (listIterator.hasNext())
		{
			List<Integer> list = listIterator.next();
			if (list != null && list.size() == length)
			{
				listIterator.remove();
			}
		}
	}

	private static void printResults(List<List<Integer>> results)
	{
		List<Integer> largestList = getLargestList(results);
		StringBuilder stringBuilder = new StringBuilder();

		ListIterator<Integer> listIterator = largestList.listIterator(largestList.size());

		while (listIterator.hasPrevious())
		{
			stringBuilder.append(inputValues.length - listIterator.previous());
			stringBuilder.append(" ");
		}

		System.out.println(largestList.size());
		System.out.println(stringBuilder.toString());
	}

	public static void main(String[] args)
	{
		inputValues = getInputValues();
		long startTime = System.currentTimeMillis();
		List<List<Integer>> results = new ArrayList<>(inputValues.length);
		for (int i = 0; i < inputValues.length; i++)
		{
			if (inputValues[i] < minValue)
			{
				List<Integer> newList = new ArrayList<>();
				newList.add(i);
				results.add(newList);
				minValue = inputValues[i];
			}
			else if (inputValues[i] > maxValue)
			{
				List<Integer> newList = new ArrayList<>(getLargestList(results));
				newList.add(i);
				results.add(newList);
				maxValue = inputValues[i];
			}
			else
			{
				List<Integer> newList = new ArrayList<>(getLargestList(results, inputValues[i]));
				newList.add(i);
				removeListByLength(results, newList.size());
				results.add(newList);
				restoreMinAndMax(results);
			}
		}

		printResults(results);
		System.out.println(String.format("Time spend: %s ms", System.currentTimeMillis() - startTime));
	}
}
