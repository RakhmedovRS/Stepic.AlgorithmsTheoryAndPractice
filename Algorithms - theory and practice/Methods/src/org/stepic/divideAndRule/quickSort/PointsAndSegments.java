package org.stepic.divideAndRule.quickSort;

import java.util.Scanner;

/**
 * В первой строке задано два целых числа 1≤n≤50000 и 1≤m≤50000 — количество отрезков и точек на прямой, соответственно.
 * Следующие n строк содержат по два целых числа ai и bi (ai≤bi) — координаты концов отрезков. Последняя строка содержит
 * m целых чисел — координаты точек. Все координаты не превышают 10^8 по модулю. Точка считается принадлежащей отрезку,
 * если она находится внутри него или на границе. Для каждой точки в порядке появления во вводе выведите,
 * скольким отрезкам она принадлежит.
 *
 * @author rassoll
 * @created 09.03.2018
 * @$Author$
 * @$Revision$
 */
public class PointsAndSegments
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int segmentsNumber = scanner.nextInt();
		int pointsNumber = scanner.nextInt();

		Segments segmentsSortedByLeftPoints = new Segments(segmentsNumber, true);
		Segments segmentsSortedByRightPoints = new Segments(segmentsNumber, false);

		int leftPoint;
		int rightPoint;
		for (int i = 0; i < segmentsNumber; i++)
		{
			leftPoint = scanner.nextInt();
			rightPoint = scanner.nextInt();

			segmentsSortedByLeftPoints.addSegment(leftPoint, rightPoint);
			segmentsSortedByRightPoints.addSegment(leftPoint, rightPoint);
		}

		segmentsSortedByLeftPoints.quickSort(0, segmentsNumber - 1);
		segmentsSortedByRightPoints.quickSort(0, segmentsNumber - 1);
		StringBuilder sb = new StringBuilder();
		for (int pointCounter = 0; pointCounter < pointsNumber; pointCounter++)
		{
			int point = scanner.nextInt();

			int leftMismatch = segmentsSortedByLeftPoints.getLeftSortedMisses(point);
			if (leftMismatch == segmentsNumber)
			{
				sb.append(0);
			}
			else
			{
				int rightMismatch = segmentsSortedByRightPoints.getRightSortedMisses(point);
				int result = segmentsNumber - (leftMismatch + rightMismatch);
				sb.append(result);
			}

			sb.append(" ");
		}

		System.out.println(sb.toString());
	}

	private static class Segment
	{
		int left;
		int right;

		private Segment(int left, int right)
		{
			this.left = left;
			this.right = right;
		}
	}

	private static class Segments
	{
		Segment[] segmentsArray;
		int segmentsCounter;
		boolean sortByLeft;

		private Segments(int segmentsCount, boolean sortByLeft)
		{
			this.segmentsArray = new Segment[segmentsCount];
			this.segmentsCounter = 0;
			this.sortByLeft = sortByLeft;
		}

		private void addSegment(int left, int right)
		{
			segmentsArray[segmentsCounter++] = new Segment(left, right);
		}

		private void quickSort(int leftEnd, int rightEnd)
		{
			if ((rightEnd - leftEnd + 1) < 10)
			{
				insertionSort(leftEnd, rightEnd);
			}
			else
			{
				int median = medianOf3(leftEnd, rightEnd);
				int partition = partitionIt(leftEnd, rightEnd, median);
				quickSort(leftEnd, partition - 1);
				quickSort(partition + 1, rightEnd);
			}
		}

		private int partitionIt(int leftEnd, int rightEnd, int median)
		{
			int leftPart = leftEnd;
			int rightPart = rightEnd - 1;

			while (true)
			{
				while (getSegmentEnd(++leftPart) < median)
				{
				}

				while (getSegmentEnd(--rightPart) > median)
				{
				}

				if (leftPart >= rightPart)
				{
					break;
				}
				else
				{
					swap(leftPart, rightPart);
				}
			}
			swap(leftPart, rightEnd - 1);
			return leftPart;
		}

		private int medianOf3(int left, int right)
		{
			int center = (left + right) / 2;

			if (getSegmentEnd(left) > getSegmentEnd(center))
			{
				swap(left, center);
			}

			if (getSegmentEnd(left) > getSegmentEnd(right))
			{
				swap(left, right);
			}

			if (getSegmentEnd(center) > getSegmentEnd(right))
			{
				swap(center, right);
			}

			swap(center, right - 1);
			return getSegmentEnd(right - 1);
		}

		private void swap(int left, int right)
		{
			Segment leftSegment = segmentsArray[left];
			segmentsArray[left] = segmentsArray[right];
			segmentsArray[right] = leftSegment;
		}

		private void insertionSort(int left, int right)
		{
			int in, out;

			for (out = left + 1; out <= right; out++)
			{
				Segment temp = segmentsArray[out];
				in = out;

				while (in > left && getSegmentEnd(in - 1) >= (sortByLeft ? temp.left : temp.right))
				{
					segmentsArray[in] = segmentsArray[in - 1];
					--in;
				}
				segmentsArray[in] = temp;
			}
		}

		private int getSegmentEnd(int segmentIndex)
		{
			return sortByLeft ? segmentsArray[segmentIndex].left : segmentsArray[segmentIndex].right;
		}

		private int getLeftSortedMisses(int point)
		{
			int retValue = 0;

			int leftSize = 0;
			int rightSize = segmentsArray.length - 1;
			int center;

			if (point < segmentsArray[leftSize].left)
			{
				return segmentsArray.length;
			}
			else if (point >= segmentsArray[rightSize].left)
			{
				return 0;
			}

			while (true)
			{
				center = (leftSize + rightSize) / 2;
				if (point < segmentsArray[center].left)
				{
					rightSize = center - 1;
					retValue = center;
				}
				else
				{
					leftSize = center + 1;
				}

				if (leftSize > rightSize)
				{
					break;
				}
			}

			if (retValue == 0)
			{
				retValue++;
			}

			return segmentsArray.length - retValue;
		}

		private int getRightSortedMisses(int point)
		{
			int retValue = 0;

			int leftSize = 0;
			int rightSize = segmentsArray.length - 1;
			int center;

			if (point > segmentsArray[rightSize].right)
			{
				return segmentsArray.length;
			}

			while (true)
			{
				center = (leftSize + rightSize) / 2;
				if (point > segmentsArray[center].right)
				{
					retValue = center + 1;
					leftSize = center + 1;
				}
				else
				{
					rightSize = center - 1;
				}

				if (leftSize > rightSize)
				{
					break;
				}
			}

			return retValue;
		}
	}
}