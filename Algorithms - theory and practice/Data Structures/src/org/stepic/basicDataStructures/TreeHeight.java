package org.stepic.basicDataStructures;

import java.util.Scanner;

/**
 * @author rassoll
 * @created 06.07.2018
 * @$Author$
 * @$Revision$
 */
public class TreeHeight
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int root = -1;
		int[] nodes = new int[scanner.nextInt()];

		for (int i = 0; i < nodes.length; i++)
		{
			int node = scanner.nextInt();
			root = node == -1 ? i : root;
			nodes[i] = node;
		}
		scanner.close();

		int maxHeight = 0;

		for (int node : nodes)
		{
			int parent = node;
			int height = 1;
			while (true)
			{
				if (parent != -1)
				{
					height++;
					parent = nodes[parent];
				}
				else
				{
					maxHeight = height > maxHeight ? height : maxHeight;
					break;
				}
			}
		}

		System.out.println(maxHeight);
	}
}
