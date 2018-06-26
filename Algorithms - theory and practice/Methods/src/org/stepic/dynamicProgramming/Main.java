package org.stepic.dynamicProgramming;

import java.util.*;

public class Main
{
	private static int[] inputValues = getInputValues();
	private static List<List<Integer>> results = new LinkedList<>();
	private static int minValue = Integer.MAX_VALUE;
	private static int maxValue = Integer.MIN_VALUE;
	private static boolean needRestoreMax = false;
	private static int counterRestoreMax = 0;
	private static int counterGetLargestList1 = 0;
	private static int counterGetLargestList2 = 0;
	private static int counterRemoveListByLength = 0;
	private static int counterRestoreMaxCall = 0;
	private static int counterGetLargestList1Call = 0;
	private static int counterGetLargestList2Call = 0;
	private static int counterRemoveListByLengthCall = 0;

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

	private static void restoreMax()
	{
		maxValue = Integer.MIN_VALUE;

		counterRestoreMaxCall++;
		for (List<Integer> list : results)
		{
			counterRestoreMax++;
			if (list != null)
			{
				int index = list.listIterator(list.size()).previous();
				if (inputValues[index] > maxValue)
				{
					maxValue = inputValues[index];
				}
			}
		}

		needRestoreMax = false;
	}

	private static List<Integer> getLargestList()
	{
		List<Integer> largestList = null;
		counterGetLargestList1Call++;
		for (List<Integer> list : results)
		{
			counterGetLargestList1++;
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

	private static List<Integer> getLargestList(int largestValue)
	{
		List<Integer> largestList = null;
		int maxLength = Integer.MIN_VALUE;

		counterGetLargestList2Call++;
		for (List<Integer> list : results)
		{
			counterGetLargestList2++;
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

	private static void removeListByLength(int length)
	{
		ListIterator<List<Integer>> listIterator = results.listIterator();

		counterRemoveListByLengthCall++;
		while (listIterator.hasNext())
		{
			counterRemoveListByLength++;
			List<Integer> list = listIterator.next();
			if (list != null && list.size() == length)
			{
				if (list.listIterator(list.size()).previous() == maxValue)
				{
					needRestoreMax = true;
				}
				listIterator.remove();
			}
		}
	}

	private static void printResults()
	{
		List<Integer> largestList = getLargestList();
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
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < inputValues.length; i++)
		{
			if (inputValues[i] < minValue)
			{
				List<Integer> newList = new LinkedList<>();
				newList.add(i);
				results.add(newList);
				minValue = inputValues[i];
			}
			else if (inputValues[i] > maxValue)
			{
				List<Integer> newList = new LinkedList<>();
				newList.addAll(getLargestList());
				newList.add(i);
				results.add(newList);
				maxValue = inputValues[i];
			}
			else
			{
				List<Integer> newList = new LinkedList<>(getLargestList(inputValues[i]));
				newList.add(i);
				removeListByLength(newList.size());
				results.add(newList);
				if (needRestoreMax)
				{
					restoreMax();
				}
			}
		}

		printResults();
		System.out.println(String.format("Time spend: %s ms", System.currentTimeMillis() - startTime));
		System.out.println(String.format("counterRestoreMax: %s , calls: %s", counterRestoreMax, counterRestoreMaxCall));
		System.out.println(String.format("counterGetLargestList1: %s , calls: %s", counterGetLargestList1, counterGetLargestList1Call));
		System.out.println(String.format("counterGetLargestList2: %s , calls: %s", counterGetLargestList2, counterGetLargestList2Call));
		System.out.println(String.format("counterRemoveListByLength: %s , calls: %s", counterRemoveListByLength, counterRemoveListByLengthCall));
	}
}
