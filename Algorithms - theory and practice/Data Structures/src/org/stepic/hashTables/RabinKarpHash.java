package org.stepic.hashTables;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class RabinKarpHash
{
	private static final short DIVIDER = 10_007;
	private static final short BASE = 47;
	private static final short STEPS = 6;

	private static int subStringHashCode;
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

	private static int hashCode(char[] pattern)
	{
		int hashCode = 0;
		int power = 0;
		for (char ch : pattern)
		{
			hashCode = (((hashCode + (ch * powers[power++])) % DIVIDER) + DIVIDER) % DIVIDER;
		}

		return hashCode;
	}

	private static int hashCode(char[] text, int i)
	{
		int hashCode = 0;

		if (i != text.length - patternLength)
		{
			hashCode = ((subStringHashCode - (text[i + patternLength] * powers[patternLength - 1]) % DIVIDER) + DIVIDER) % DIVIDER;
			hashCode = (hashCode * BASE + text[i]) % DIVIDER;
		}
		else
		{
			for (int power = 0, pos = i; pos < i + patternLength; pos++, power++)
			{
				hashCode = (hashCode + (text[pos] * powers[power]) % DIVIDER) % DIVIDER;
			}
		}

		return hashCode;
	}

	public static void main(String[] args) throws IOException
	{
		try (Scanner scanner = new Scanner(System.in))
		{
			char[] pattern = scanner.next().toCharArray();
			char[] text = scanner.next().toCharArray();

			patternLength = pattern.length;
			fillPowers();

			int patternHashCode = hashCode(pattern);

			LinkedList<Integer> matches = new LinkedList<>();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

			for (int i = text.length - pattern.length; i >= 0; i--)
			{
				subStringHashCode = hashCode(text, i);

				if (patternHashCode == subStringHashCode)
				{
					boolean equals = true;

					for (int pLeft = 0, pRight = patternLength - 1, tLeft = i, tRight = i + pRight, steps = 0;
						tLeft < tRight && steps <= STEPS; pLeft++, tLeft++, pRight--, tRight--, steps++)
					{
						if (pattern[pLeft] != text[tLeft] || pattern[pRight] != text[tRight])
						{
							equals = false;
							break;
						}
					}

					if (equals)
					{
						matches.addFirst(i);
					}
				}
			}

			for (Integer match : matches)
			{
				writer.write(String.format("%s ", match.toString()));
			}

			writer.close();
		}
	}
}


