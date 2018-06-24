package org.stepic.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Main
{
	private static int[] inputValues;
	private static boolean directOrder = false;

	private static int[] getInputValues()
	{
		Scanner scanner = new Scanner(System.in);

		int elementsNumber = scanner.nextInt();
		int[] input = new int[elementsNumber];
		if (directOrder)
		{
			for (int i = 0; i < elementsNumber; i++)
			{
				input[i] = scanner.nextInt();
			}
		}
		else
		{
			for (int i = elementsNumber - 1; i >= 0; i--)
			{
				input[i] = scanner.nextInt();
			}
		}

		return input;
	}

	private static boolean isSmallestOfAllEndCandidates(List<List<Integer>> inputCollection, int inputValue)
	{
		int minValue = Integer.MAX_VALUE;
		for (List<Integer> list : inputCollection)
		{
			if (list != null)
			{
				int index = list.get(list.size() - 1);
				if (minValue > inputValues[index])
				{
					minValue = inputValues[index];
				}
			}
		}

		return inputValue < minValue;
	}

	private static boolean isLargestOfAllEndCandidates(List<List<Integer>> inputCollection, int inputValue)
	{
		int maxValue = Integer.MIN_VALUE;
		for (List<Integer> list : inputCollection)
		{
			if (list != null)
			{
				int index = list.get(list.size() - 1);
				if (inputValues[index] > maxValue)
				{
					maxValue = inputValues[index];
				}
			}
		}
		return inputValue > maxValue;
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
			int largestListElement = inputValues[list.get(list.size() - 1)];
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

		if (directOrder)
		{
			for (int i = 0; i < largestList.size(); i++)
			{
				stringBuilder.append(inputValues.length - largestList.get(i));
				stringBuilder.append(" ");
			}
		}
		else
		{
			ListIterator<Integer> listIterator = largestList.listIterator(largestList.size());

			while (listIterator.hasPrevious())
			{
				stringBuilder.append(inputValues.length - listIterator.previous());
				stringBuilder.append(" ");
			}
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
			if (isSmallestOfAllEndCandidates(results, inputValues[i]))
			{
				List<Integer> newList = new ArrayList<>();
				newList.add(i);
				results.add(newList);
			}
			else if (isLargestOfAllEndCandidates(results, inputValues[i]))
			{
				List<Integer> newList = new ArrayList<>(getLargestList(results));
				newList.add(i);
				results.add(0, newList);
			}
			else
			{
				List<Integer> newList = new ArrayList<>(getLargestList(results, inputValues[i]));
				newList.add(i);
				removeListByLength(results, newList.size());
				results.add(0, newList);
			}
		}

		printResults(results);
		System.out.println(String.format("Time spend: %s ms", System.currentTimeMillis() - startTime));
	}
}
