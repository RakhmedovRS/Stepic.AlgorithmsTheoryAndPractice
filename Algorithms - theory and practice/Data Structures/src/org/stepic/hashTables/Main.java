package org.stepic.hashTables;

import java.util.Scanner;

public class Main
{
	private static final int DIVIDER = 1_000_000_007;
	private static final int BASE = 263;
	private static long power;

	private static long pow(int pow)
	{
		long result = 1;
		for (int i = 0; i < pow; i++)
		{
			result = (result * BASE) % DIVIDER;
		}

		return result;
	}

	private static int hashCode(String string)
	{
		long hashCode = 0;
		int i = 0;
		for (char ch : string.toCharArray())
		{
			hashCode = (((hashCode + (ch * pow(i))) % DIVIDER) + DIVIDER) % DIVIDER;
			i++;
		}

		return (int) hashCode;
	}

	private static int hashCode(char[] textChars, int i, int oldHashCode, int patternLength)
	{
		long hashCode = 0;

		if (i != 0)
		{
			hashCode = (oldHashCode - textChars[i - 1]) / BASE + (textChars[i - 1 + patternLength] * power) % DIVIDER;
		}
		else
		{
			for (int pos = i; pos < i + patternLength; pos++)
			{
				hashCode = (((hashCode + (textChars[pos] * pow(pos))) % DIVIDER) + DIVIDER) % DIVIDER;
			}
		}

		return (int) hashCode;
	}

	public static void main(String[] args)
	{
		try (Scanner scanner = new Scanner(System.in))
		{
			String pattern = scanner.next();
			String text = scanner.next();
			power = pow(pattern.length() - 1);
			int subStringHashCode = 0;
			int patternHashCode = hashCode(pattern);

			char[] textChars = text.toCharArray();
			char[] patternChars = pattern.toCharArray();

			StringBuilder stringBuilder = new StringBuilder();

			int i = 0;
			while (i + pattern.length() <= textChars.length)
			{
				subStringHashCode = hashCode(textChars, i, subStringHashCode, pattern.length());

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
						stringBuilder.append(i);
						stringBuilder.append(" ");
					}
				}
				i++;
			}

			System.out.println(stringBuilder.toString());
		}
	}
}
