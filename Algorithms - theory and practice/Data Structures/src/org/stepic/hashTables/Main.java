package org.stepic.hashTables;

import java.util.LinkedList;
import java.util.Scanner;

public class Main
{
	private static final int DIVIDER = 1007;
	private static int base;
	private static long power;
	private static long subStringHashCode;

	private static long pow(int pow)
	{
		long result = 1;
		for (int i = 0; i < pow; i++)
		{
			result = (result * base) % DIVIDER;
		}

		return result;
	}

	private static long hashCode(String string)
	{
		long hashCode = 0;
		int i = 0;
		for (char ch : string.toCharArray())
		{
			hashCode = (((hashCode + (ch * pow(i))) % DIVIDER) + DIVIDER) % DIVIDER;
			i++;
		}

		return hashCode;
	}

	private static long hashCode(char[] textChars, int i, int patternLength)
	{
		long hashCode = 0;

		if (i != textChars.length - patternLength)
		{
			hashCode = ((subStringHashCode - (textChars[i + patternLength] * power) % DIVIDER) + DIVIDER) % DIVIDER;
			hashCode = (hashCode * base + textChars[i]) % DIVIDER;
		}
		else
		{
			for (int pow = 0, pos = i; pos < i + patternLength; pos++, pow++)
			{
				hashCode = (hashCode + (textChars[pos] * pow(pow)) % DIVIDER) % DIVIDER;
			}
		}

		return hashCode;
	}

	public static void main(String[] args)
	{
		try (Scanner scanner = new Scanner(System.in))
		{
			String pattern = scanner.next();
			String text = scanner.next();

			base = pattern.length() == 1 ? 1 : pattern.length() - 1;
			power = pow(pattern.length() - 1);

			long patternHashCode = hashCode(pattern);

			char[] textChars = text.toCharArray();
			char[] patternChars = pattern.toCharArray();

			LinkedList<String> answer = new LinkedList<>();

			int i = textChars.length - pattern.length();
			while (i >= 0)
			{
				subStringHashCode = hashCode(textChars, i, pattern.length());

				if (patternHashCode == subStringHashCode)
				{
					boolean equals = true;
					for (int p = 0, t = i; t < i + pattern.length(); t++, p++)
					{
						if (patternChars[p] != textChars[t])
						{
							equals = false;
						}
					}

					if (equals)
					{
						answer.addFirst(String.valueOf(i));
					}
				}
				i--;
			}

			System.out.println(answer.toString().replaceAll("\\[", "").replaceAll("]", "").replaceAll(",", ""));
		}
	}
}
