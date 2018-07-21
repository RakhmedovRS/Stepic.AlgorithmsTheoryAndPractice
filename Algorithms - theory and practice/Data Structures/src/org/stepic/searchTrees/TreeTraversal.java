package org.stepic.searchTrees;

import java.util.Scanner;

public class TreeTraversal
{
	private static Node[] nodes;
	private static StringBuilder inOrder = new StringBuilder();
	private static StringBuilder preOrder = new StringBuilder();
	private static StringBuilder postOrder = new StringBuilder();

	private static class Node
	{
		int value;
		int left;
		int right;

		private Node(int value, int left, int right)
		{
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}

	private static void walk(int index)
	{
		if (index == -1)
		{
			return;
		}

		preOrder.append(nodes[index].value);
		preOrder.append(" ");

		walk(nodes[index].left);

		inOrder.append(nodes[index].value);
		inOrder.append(" ");

		walk(nodes[index].right);

		postOrder.append(nodes[index].value);
		postOrder.append(" ");
	}

	public static void main(String[] args)
	{
		try (Scanner scanner = new Scanner(System.in))
		{
			int vertexCount = scanner.nextInt();
			nodes = new Node[vertexCount];
			for (int i = 0; i < vertexCount; i++)
			{
				nodes[i] = new Node(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
			}

			walk(0);
			System.out.println(inOrder.toString());
			System.out.println(preOrder.toString());
			System.out.println(postOrder.toString());
		}
	}
}
