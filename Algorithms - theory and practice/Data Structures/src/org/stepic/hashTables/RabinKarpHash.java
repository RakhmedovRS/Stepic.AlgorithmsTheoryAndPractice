package org.stepic.hashTables;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class RabinKarpHash
{
	private static final int DIVIDER = 10_007;
	private static final short BASE = 47;
	private static int power;
	private static int subStringHashCode;
	private static char[] textChars;
	private static int patternLength;
	private static int[] powers;

	private static void fillPowers()
	{
		powers = new int[patternLength + 1];

		powers[0] = 1;
		powers[1] = BASE;
		if (patternLength >= 3)
		{
			for (int power = 2; power <= patternLength - 1; power++)
			{
				powers[power] = (powers[power - 1] * BASE) % DIVIDER;
			}
		}
	}

	private static int hashCode(String string)
	{
		int hashCode = 0;
		int power = 0;
		for (char ch : string.toCharArray())
		{
			hashCode = (((hashCode + (ch * powers[power++])) % DIVIDER) + DIVIDER) % DIVIDER;
		}

		return hashCode;
	}

	private static int hashCode(int i)
	{
		int hashCode = 0;

		if (i != textChars.length - patternLength)
		{
			hashCode = ((subStringHashCode - (textChars[i + patternLength] * power) % DIVIDER) + DIVIDER) % DIVIDER;
			hashCode = (hashCode * BASE + textChars[i]) % DIVIDER;
		}
		else
		{
			for (int power = 0, pos = i; pos < i + patternLength; pos++, power++)
			{
				hashCode = (hashCode + (textChars[pos] * powers[power]) % DIVIDER) % DIVIDER;
			}
		}

		return hashCode;
	}

	public static void main(String[] args) throws IOException
	{
		try (Scanner scanner = new Scanner(System.in))
		{
			String pattern = scanner.next();
			String text = scanner.next();

			patternLength = pattern.length();
			fillPowers();
			power = powers[pattern.length() - 1];

			long patternHashCode = hashCode(pattern);

			textChars = text.toCharArray();
			char[] patternChars = pattern.toCharArray();

			LinkedList<Integer> matches = new LinkedList<>();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

			int i = textChars.length - pattern.length();
			while (i >= 0)
			{
				subStringHashCode = hashCode(i);

				if (patternHashCode == subStringHashCode)
				{
					boolean equals = true;

					int steps = 0;
					for (int pLeft = 0, pRight = patternLength - 1,
						tLeft = i, tRight = i + pattern.length() - 1;
						tLeft < tRight;
						pLeft++, tLeft++, pRight--, tRight--)
					{
						if (steps > 6)
						{
							break;
						}

						if (patternChars[pLeft] != textChars[tLeft]
							|| patternChars[pRight] != textChars[tRight])
						{
							equals = false;
							break;
						}

						steps++;
					}

					if (equals)
					{
						matches.addFirst(i);
					}
				}
				i--;
			}

			for (Integer match : matches)
			{
				writer.write(match.toString());
				writer.write(" ");
			}

			writer.close();
		}
	}
}


