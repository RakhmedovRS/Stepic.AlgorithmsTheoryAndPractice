package org.stepic.dynamicProgramming;

import java.util.Scanner;

/**
 * Вычислите расстояние редактирования двух данных непустых строк длины не более 10^2, содержащих строчные буквы латинского алфавита.
 *
 * @author rassoll
 * @created 03.07.2018
 * @$Author$
 * @$Revision$
 */
public class EditingDistance
{
	private static int min(int first, int second, int third)
	{
		if (first > second)
		{
			return second > third ? third : second;
		}
		else
		{
			return first > third ? third : first;
		}
	}

	private static int findEditDistance(String inputString, String targetString)
	{
		int[][] results = new int[inputString.length() + 1][targetString.length() + 1];
		for (int i = 0; i <= inputString.length(); i++)
		{
			results[i][0] = i;
		}
		for (int j = 0; j <= targetString.length(); j++)
		{
			results[0][j] = j;
		}

		for (int i = 1; i <= inputString.length(); i++)
		{
			for (int j = 1; j <= targetString.length(); j++)
			{
				int diff = inputString.charAt(i - 1) == targetString.charAt(j - 1) ? 0 : 1;
				results[i][j] = min(
					/*вставка*/
					results[i][j - 1] + 1,
					/*удаление*/
					results[i - 1][j] + 1,
					/*несоответствие*/
					results[i - 1][j - 1] + diff);
			}
		}

		return results[inputString.length()][targetString.length()];
	}

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		String inputString = scanner.next();
		String targetString = scanner.next();

		if (inputString.equals(targetString))
		{
			System.out.println(0);
		}
		else if (targetString.length() == 0)
		{
			System.out.println(inputString.length());
		}
		else if (inputString.length() == 0)
		{
			System.out.println(targetString.length());
		}
		else
		{
			System.out.println(findEditDistance(inputString, targetString));
		}
	}
}
