package org.stepic.systemsOfDisjointSets;

import java.util.Scanner;

public class JoinTables
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int tablesCount = scanner.nextInt();
		int requestsCount = scanner.nextInt();
		Set set = new Set(tablesCount);
		for (int i = 0; i < tablesCount; i++)
		{
			set.initSet(scanner.nextInt(), i);
		}

		for (int i = 0; i < requestsCount; i++)
		{
			set.union(scanner.nextInt() - 1, scanner.nextInt() - 1);
			System.out.println(set.maxRank);
		}
	}

	private static class Set
	{
		private int[] rank;
		private int[] set;
		private int maxRank;

		Set(int tablesCount)
		{
			this.set = new int[tablesCount];
			rank = new int[tablesCount];
		}

		void initSet(int entryCount, int position)
		{
			set[position] = position;
			rank[position] = entryCount;
			maxRank = entryCount > maxRank ? entryCount : maxRank;
		}

		int find(int i)
		{
			if (i != set[i])
			{
				set[i] = find(set[i]);
			}
			return set[i];
		}

		void union(int destination, int source)
		{
			int destinationID = find(destination);
			int sourceID = find(source);

			if (destinationID == sourceID)
			{
				return;
			}

			set[sourceID] = destinationID;
			rank[destinationID] += rank[sourceID];
			maxRank = rank[destinationID] > maxRank ? rank[destinationID] : maxRank;
		}
	}
}
