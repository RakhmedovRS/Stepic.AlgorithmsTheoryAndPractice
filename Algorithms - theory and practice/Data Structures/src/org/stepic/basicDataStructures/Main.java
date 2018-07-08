package org.stepic.basicDataStructures;

import java.util.Arrays;
import java.util.Scanner;

public class Main
{
	private static boolean process(Package[] buffer, int average, int duration)
	{
		int minValueIndex = -1;
		for (int i = 0; i < buffer.length; i++)
		{
			if (average >= buffer[i].getEndTime())
			{
				if (minValueIndex == -1 || buffer[minValueIndex].getEndTime() > buffer[i].getEndTime())
				{
					minValueIndex = i;
				}
			}
		}

		if (minValueIndex != -1)
		{
			buffer[minValueIndex] = new Package(average, duration);
			return true;
		}
		else
		{
			return false;
		}
	}

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		Package[] buffer = new Package[scanner.nextInt()];
		Arrays.fill(buffer, new Package(0, Integer.MIN_VALUE));
		int packageCount = scanner.nextInt();

		if (packageCount == 0)
		{
			System.out.println("");
		}
		else if (packageCount == 1)
		{
			System.out.println(scanner.nextInt());
		}
		else
		{
			for (int i = 0; i < packageCount; i++)
			{
				int average = scanner.nextInt();
				int duration = scanner.nextInt();

				if (process(buffer, average, duration))
				{
					System.out.println(average);
				}
				else
				{
					System.out.println("-1");
				}
			}
			scanner.close();
		}
	}

	private static class Package
	{
		int average;
		int duration;

		Package(int average, int duration)
		{
			this.average = average;
			this.duration = duration;
		}

		int getEndTime()
		{
			return average + duration;
		}
	}
}
