package org.stepic.dynamicProgramming.overview;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int[] stairs = new int[scanner.nextInt()];
		int[] result = new int[stairs.length];
		for (int i = 0; i < stairs.length; i++)
		{
			stairs[i] = scanner.nextInt();
		}

		if (stairs.length == 0)
		{
			System.out.println(0);
		}
		else if (stairs.length == 1)
		{
			System.out.println(stairs[0]);
		}
		else if (stairs.length == 2)
		{
			System.out.println(stairs[0] + stairs[1]);
		}
		else
		{
			result[0] = stairs[0];
			result[1] = stairs[1];
			for (int i = 2; i < stairs.length; i++)
			{
				result[i] = Math.max(result[i - 2] + stairs[i], result[i - 1] + stairs[i]);
			}
			System.out.println(result[stairs.length - 1]);
		}
	}
}
