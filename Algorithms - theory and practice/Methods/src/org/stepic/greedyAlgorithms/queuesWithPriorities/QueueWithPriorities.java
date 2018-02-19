package org.stepic.greedyAlgorithms.queuesWithPriorities;

import java.util.Scanner;

/**
 * @author rassoll
 * @created 19.02.2018
 * @$Author$
 * @$Revision$
 */

class Node
{
	private int key;

	Node(int key)
	{
		this.key = key;
	}

	/**
	 * @return ключ
	 */
	int getKey()
	{
		return key;
	}

	/**
	 * Установить значение ключа
	 *
	 * @param key ключ
	 */
	void setKey(int key)
	{
		this.key = key;
	}
}

class Heap
{
	private Node[] heapArray;
	private int maxSize;
	private int currentSize;

	Heap(int maxSize)
	{
		this.maxSize = maxSize;
		currentSize = 0;
		heapArray = new Node[maxSize];
	}

	/**
	 * Вставить ключ в кучу
	 *
	 * @param key ключ
	 * @return признак успешности вставки
	 */
	boolean insert(int key)
	{
		if (currentSize == maxSize)
		{
			return false;
		}

		Node newNode = new Node(key);
		heapArray[currentSize] = newNode;
		tickleUp(currentSize++);
		return true;
	}

	/**
	 * Выполнить удаление узла из кучи
	 *
	 * @return удаленный узел
	 */
	Node remove()
	{
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
	}

	/**
	 * Выполнить перемещение узла вверх по куче
	 *
	 * @param index индекс перемещаемого узла
	 */
	void tickleUp(int index)
	{
		int parent = (index - 1) / 2;
		Node bottom = heapArray[index];

		while (index > 0 && heapArray[parent].getKey() < bottom.getKey())
		{
			heapArray[index] = heapArray[parent];
			index = parent;
			parent = (parent - 1) / 2;
		}
		heapArray[index] = bottom;
	}

	/**
	 * Выполнить перемещение узла вниз по куче
	 *
	 * @param index индекс перемещаемого узла
	 */
	void trickleDown(int index)
	{
		int largerChild;
		Node top = heapArray[index];

		while (index < currentSize / 2)
		{
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;

			/*определим наибольшего потомка*/
			if (rightChild < currentSize
				&& heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
			{
				largerChild = rightChild;
			}
			else
			{
				largerChild = leftChild;
			}

			if (top.getKey() >= heapArray[largerChild].getKey())
			{
				break;
			}

			heapArray[index] = heapArray[largerChild];
			index = largerChild;
		}
		heapArray[index] = top;
	}
}

public class QueueWithPriorities
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int size = scanner.nextInt();
		Heap heap = new Heap(size);
		for (int i = 0; i < size; i++)
		{
			if (scanner.next().toLowerCase().equals("extractmax"))
			{
				System.out.println(heap.remove().getKey());
			}
			else
			{
				heap.insert(scanner.nextInt());
			}
		}
	}
}
