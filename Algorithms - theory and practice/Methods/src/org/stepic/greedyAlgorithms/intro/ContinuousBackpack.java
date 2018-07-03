package org.stepic.greedyAlgorithms.intro;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Первая строка содержит количество предметов 1≤n≤10^3 1≤n≤10^3 и вместимость рюкзака 0≤W≤2⋅10^6 0≤W≤2⋅10^6.
 * Каждая из следующих nn строк задаёт стоимость 0≤ci≤2⋅10^6 0≤ci≤2⋅10^6 и объём 0<wi≤2⋅1060<wi≤2⋅106 предмета
 * (nn, WW, cici, wiwi — целые числа). Выведите максимальную стоимость частей предметов
 * (от каждого предмета можно отделить любую часть, стоимость и объём при этом пропорционально уменьшатся),
 * помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.
 *
 * @author rassoll
 * @created 28.09.2017
 * @$Author$
 * @$Revision$
 */
public class ContinuousBackpack
{
    //[Experimental feedback] Your code complexity score is 51.32 (less is better).
    private static class Subject implements Comparable<Subject>
    {
        public Integer cost;
        public Integer size;

        public Subject(Integer cost, Integer size)
        {
            this.cost = cost;
            this.size = size;
        }

        public Double getSubjectCost()
        {
            return cost.doubleValue()/size.doubleValue();
        }

        public Double getPartOfTheSubjectCost(Integer size)
        {
            return (cost.doubleValue()/this.size.doubleValue()) * size;
        }

        public static Comparator<Subject> subjectComparator = new Comparator<Subject>()
        {
            @Override
            public int compare(Subject subject1, Subject subject2)
            {
                if (subject1 == null)
                {
                    return -1;
                }
                if (subject2 == null)
                {
                    return 1;
                }
                if (subject1.equals(subject2))
                {
                    return 0;
                }

                return subject1.compareTo(subject2);
            }
        };

        @Override
        public int compareTo(Subject subject)
        {
            if (getSubjectCost() < subject.getSubjectCost())
            {
                return 1;
            } else
            {
                return -1;
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        Integer numberOfSubjects = scan.nextInt();
        Integer backpackCapacity = scan.nextInt();
        List<Subject> subjects = new ArrayList<>();

        for (int i = 0; i < numberOfSubjects; i++)
        {
            subjects.add(i, new Subject(scan.nextInt(), scan.nextInt()));
        }

        subjects.sort(Subject.subjectComparator);

        printTotalCost(subjects, backpackCapacity);
    }

    private static void printTotalCost(List<Subject> subjects, Integer backpackCapacity)
    {
        Double totalCost = 0D;
        for (int i = 0; i < subjects.size(); i++)
        {
            if (backpackCapacity >= subjects.get(i).size)
            {
                totalCost += subjects.get(i).cost;
                backpackCapacity -= subjects.get(i).size;
            }
            else if (backpackCapacity <  subjects.get(i).size)
            {
                totalCost += subjects.get(i).getPartOfTheSubjectCost(backpackCapacity);
                backpackCapacity -= backpackCapacity;
            }

            if (backpackCapacity == 0)
            {
                break;
            }
        }

        System.out.println(new DecimalFormat("#0.000").format(totalCost));
    }
}
