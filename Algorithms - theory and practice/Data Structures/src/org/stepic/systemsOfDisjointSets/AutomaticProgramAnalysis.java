package org.stepic.systemsOfDisjointSets;

import java.util.Scanner;

public class AutomaticProgramAnalysis
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int maxNumber = scanner.nextInt();
		int numberOfEqualities = scanner.nextInt();
		int numberOfInequalities = scanner.nextInt();

		Set set = new Set(maxNumber);

		int result = 1;
		for (int i = 0; i < numberOfEqualities; i++)
		{
			set.union(scanner.nextInt() - 1, scanner.nextInt() - 1);
		}

		for (int i = 0; i < numberOfInequalities; i++)
		{
			if (set.check(scanner.nextInt() - 1, scanner.nextInt() - 1))
			{
				result = 0;
				break;
			}
		}
		System.out.println(result);
	}

	private static class Set
	{
		private int[] setArray;

		Set(int size)
		{
			this.setArray = new int[size];
			for (int i = 0; i < size; i++)
			{
				this.setArray[i] = i;
			}
		}

		void union(int destination, int source)
		{
			int destinationID = find(destination);
			int sourceID = find(source);

			setArray[sourceID] = destinationID;
		}

		int find(int i)
		{
			if (i != setArray[i])
			{
				setArray[i] = find(setArray[i]);
			}
			return setArray[i];
		}

		boolean check(int left, int right)
		{
			return setArray[find(left)] == setArray[find(right)];
		}
	}
}
