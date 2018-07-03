package org.stepic.greedyAlgorithms.intro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * По данным nn отрезкам необходимо найти множество точек минимального размера,
 * для которого каждый из отрезков содержит хотя бы одну из точек.
 *
 * В первой строке дано число 1≤n≤100 1≤n≤100 отрезков.
 * Каждая из последующих nn строк содержит по два числа 0≤l≤r≤10^9 0≤l≤r≤10^9,
 * задающих начало и конец отрезка. Выведите оптимальное число mm точек и сами mm точек.
 * Если таких множеств точек несколько, выведите любое из них.
 *
 * @author rassoll
 * @created 27.09.2017
 * @$Author$
 * @$Revision$
 */
public class CoverSegment
{

    private static class Segment implements Comparable<Segment>
    {
        public Integer leftPoint;
        public Integer rightPoint;

        public Segment(Integer leftPoint, Integer rightPoint)
        {
            this.leftPoint = leftPoint;
            this.rightPoint = rightPoint;
        }

        public boolean isPointBelongsSegment(Integer point)
        {
            return point >= leftPoint;
        }

        @Override
        public int compareTo(Segment segment)
        {
            if (this.rightPoint > segment.rightPoint)
            {
                return 1;
            } else
            {
                return -1;
            }
        }

        public static Comparator<Segment> SegmentComparator = new Comparator<Segment>()
        {
            @Override
            public int compare(Segment segment1, Segment segment2)
            {
                if (segment1 == null)
                {
                    return -1;
                }
                if (segment2 == null)
                {
                    return 1;
                }
                if (segment1.equals(segment2))
                {
                    return 0;
                }

                return segment1.compareTo(segment2);
            }
        };
    }

    //[Experimental feedback] Your code complexity score is 56.18 (less is better).
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        Integer numberOfSegments = scan.nextInt();
        List<Segment> segments = new ArrayList<>();

        for (int i = 0; i < numberOfSegments; i++)
        {
            segments.add(i, new Segment(scan.nextInt(), scan.nextInt()));
        }

        segments.sort(Segment.SegmentComparator);

        printSegments(segments);
    }

    private static void printSegments(List<Segment> segments)
    {
        List<Integer> result = new ArrayList<>();

        Integer rightPoint = 0;

        while (segments.size() > 0)
        {
            if (segments.size() != 0)
            {
                rightPoint = segments.get(0).rightPoint;
                result.add(rightPoint);
            }

            for (int i = 0; i < segments.size();)
            {
                if (segments.get(i).isPointBelongsSegment(rightPoint))
                {
                    segments.remove(i);
                }
                else
                {
                    break;
                }
            }

        }

        System.out.println(String.format("%d", result.size()));
        if (result.size() == 0)
        {
            System.out.println(String.format("%d", result.size()));
        }
        else
        {
            String output = result.get(0).toString();
            for (int i = 1; i < result.size(); i++)
            {
                output += " " + result.get(i);
            }
            System.out.println(result);
        }
    }
}