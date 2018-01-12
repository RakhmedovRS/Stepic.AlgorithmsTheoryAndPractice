package org.stepic.basicDataStructures;

import javafx.util.Pair;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author rassoll
 * @created 11.01.2018
 * @$Author$
 * @$Revision$
 */
public class Bracers
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		Stack<Pair<Character, Integer>> bracers = new Stack<>();
		char[] input = scanner.next().toCharArray();
		boolean success = true;

		for (int i = 0; i < input.length; i++)
		{
			if (!success)
			{
				break;
			}

			switch (input[i])
			{
				case '(':
				case '[':
				case '{':
					bracers.push(new Pair<>(input[i], i));
					break;
				case ')':
					if (bracers.size() == 0)
					{
						System.out.println(i + 1);
						success = false;
					}
					else
					{
						if (bracers.peek().getKey() != '(')
						{
							System.out.println(i + 1);
							success = false;
						}
						else
						{
							bracers.pop();
						}
					}
					break;
				case ']':
					if (bracers.size() == 0)
					{
						System.out.println(i + 1);
						success = false;
					}
					else
					{
						if (bracers.peek().getKey() != '[')
						{
							System.out.println(i + 1);
							success = false;
						}
						else
						{
							bracers.pop();
						}
					}
					break;
				case '}':
					if (bracers.size() == 0)
					{
						System.out.println(i + 1);
						success = false;
					}
					else
					{
						if (bracers.peek().getKey() != '{')
						{
							System.out.println(i + 1);
							success = false;
						}
						else
						{
							bracers.pop();
						}
					}
					break;
			}
			if ((i + 1 == input.length) && !bracers.isEmpty() && success)
			{
				System.out.println(bracers.peek().getValue() + 1);
				success = false;
			}
		}

		if (!bracers.isEmpty() && success)
		{
			System.out.println(input.length + 1);
		}
		else if (success)
		{
			System.out.println("Success");
		}
	}
}