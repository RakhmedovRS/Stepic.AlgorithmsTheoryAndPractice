package org.stepic.basicDataStructures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int root = -1;
		int[] nodes = new int[scanner.nextInt()];
		LinkedList<Node> nodesList = new LinkedList<>();

		for (int i = 0; i < nodes.length; i++)
		{
			int node = scanner.nextInt();
			root = node == -1 ? i : root;
			nodes[i] = node;
			nodesList.add(new Node(i, node));
		}
		scanner.close();

		List<List<Integer>> paths = new LinkedList<>();

		Iterator<Node> iterator = nodesList.iterator();
		int counter = 0;
		while (iterator.hasNext())
		{
			Node node = iterator.next();

			if (node.parentPosition == root)
			{
				LinkedList<Integer> path = new LinkedList<>();
				path.add(counter);
				paths.add(path);
				iterator.remove();
			}

			if (node.parentPosition == -1)
			{
				iterator.remove();
			}
			counter++;
		}
	}

	private static class Node
	{
		int nodePosition;
		int parentPosition;

		Node(int nodePosition, int parentPosition)
		{
			this.nodePosition = nodePosition;
			this.parentPosition = parentPosition;
		}
	}
}
