package org.stepic.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;
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
		int maxValue = Integer.MIN_VALUE;
		int maxLength = Integer.MIN_VALUE;
		for (List<Integer> list : inputCollection)
		{
			int largestListElement = inputValues[list.get(list.size() - 1)];
			if (largestListElement >= maxValue
				&& largestListElement <= largestValue
				&& list.size() > maxLength)
			{
				largestList = list;
				maxValue = largestListElement;
				maxLength = list.size();
			}
		}

		return largestList;
	}

	private static List<Integer> getLongestList(List<List<Integer>> inputCollection)
	{
		List<Integer> longestList = null;
		for (List<Integer> list : inputCollection)
		{
			if (longestList == null)
			{
				longestList = list;
			}
			else
			{
				if (list.size() > longestList.size())
				{
					longestList = list;
				}
			}
		}

		return longestList;
	}

	private static void removeListByLength(List<List<Integer>> inputCollection, int length)
	{
		for (int i = 0; i < inputCollection.size(); i++)
		{
			List<Integer> list = inputCollection.get(i);

			if (list != null && list.size() == length)
			{
				inputCollection.remove(list);
			}
		}
	}

	private static void printResults(List<List<Integer>> results)
	{
		List<Integer> largestList = getLongestList(results);
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
			for (int i = largestList.size() - 1; i >= 0; i--)
			{
				stringBuilder.append(inputValues.length - largestList.get(i));
				stringBuilder.append(" ");
			}
		}

		System.out.println(largestList.size());
		System.out.println(stringBuilder.toString());
	}

	public static void main(String[] args)
	{
		inputValues = getInputValues();
		List<List<Integer>> results = new ArrayList<>();

		for (int i = 0; i < inputValues.length; i++)
		{
			if (i == 6)
			{
				String a = "a";
			}

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
				results.add(newList);
			}
			else
			{
				List<Integer> newList = new ArrayList<>(getLargestList(results, inputValues[i]));
				newList.add(i);
				removeListByLength(results, newList.size());
				results.add(newList);
			}
		}

		printResults(results);
	}
}
