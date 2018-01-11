package org.stepic.greedyAlgorithms.huffman.task2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Восстановите строку по её коду и беспрефиксному коду символов.
 *
 * В первой строке входного файла заданы два целых числа kk и ll через пробел — количество различных букв, встречающихся в строке,
 * и размер получившейся закодированной строки, соответственно. В следующих kk строках записаны коды букв в формате "letter: code".
 * Ни один код не является префиксом другого. Буквы могут быть перечислены в любом порядке. В качестве букв могут встречаться лишь
 * строчные буквы латинского алфавита; каждая из этих букв встречается в строке хотя бы один раз. Наконец, в последней строке
 * записана закодированная строка. Исходная строка и коды всех букв непусты. Заданный код таков, что закодированная строка
 * имеет минимальный возможный размер.
 *
 * В первой строке выходного файла выведите строку ss. Она должна состоять из строчных букв латинского алфавита.
 * Гарантируется, что длина правильного ответа не превосходит 104104 символов.
 *
 * @author rassoll
 * @created 03.01.2018
 * @$Author$
 * @$Revision$
 */
public class HuffmanDecoding
{
	private static final char emptyNodeChar = 216;
	private static final char leftTurnCharacter = '0';

	/**
	 * Построить дерево Хаффмана для декодирования сообщения
	 *
	 * @param codeTable таблица двоичных кодов
	 * @return дерево Хаффмана
	 */
	private static Tree createDecodingTree(HashMap<Character, String> codeTable)
	{
		Tree huffmanDecodingTree = new Tree(new Node(emptyNodeChar, emptyNodeChar));
		Node rootNode = huffmanDecodingTree.getRoot();
		codeTable.forEach((key, value) ->
		{
			Node currentNode = rootNode;
			for (char ch : value.toCharArray())
			{
				if (ch == leftTurnCharacter)
				{
					if (currentNode.leftChild == null)
					{
						currentNode.leftChild = new Node(emptyNodeChar, emptyNodeChar);
					}
					currentNode = currentNode.leftChild;
				}
				else
				{
					if (currentNode.rightChild == null)
					{
						currentNode.rightChild = new Node(emptyNodeChar, emptyNodeChar);
					}
					currentNode = currentNode.rightChild;
				}
			}

			currentNode.key = key;
		});

		return huffmanDecodingTree;
	}

	/**
	 * Декодирование сообщения из двоичной формы
	 *
	 * @param encodedMessage закодированное сообщение
	 * @param codeTable      таблица двоичных кодов
	 * @return декодированное сообщение
	 */
	private static String decode(String encodedMessage, HashMap<Character, String> codeTable)
	{
		Tree huffmanDecodingTree = createDecodingTree(codeTable);
		StringBuilder decodedMessage = new StringBuilder();

		Node current = huffmanDecodingTree.getRoot();
		for (char ch : encodedMessage.toCharArray())
		{

			if (ch == leftTurnCharacter)
			{
				current = current.leftChild;
			}
			else
			{
				current = current.rightChild;
			}

			if (current.key != emptyNodeChar)
			{
				decodedMessage.append((char) current.key);
				current = huffmanDecodingTree.getRoot();
			}
		}

		return decodedMessage.toString();
	}

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		LinkedHashMap<Character, String> codeTable = new LinkedHashMap<>();

		int count = scanner.nextInt();
		int size = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < count; i++)
		{
			String charAndFrequency = scanner.nextLine();
			codeTable.put(
				(charAndFrequency.substring(0, charAndFrequency.indexOf(':')).trim().charAt(0)),
				charAndFrequency.substring(charAndFrequency.indexOf(':') + 1).trim());
		}
		String encodedString = scanner.next();

		codeTable = codeTable
			.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		System.out.println(decode(encodedString, codeTable));
	}

	/**
	 * Класс представляющий узел дерева
	 *
	 * @author rassoll
	 * @created 01.01.2018
	 * @$Author$
	 * @$Revision$
	 */
	private static class Node
	{
		int key;
		int value;
		Node leftChild;
		Node rightChild;

		Node(int key, int value)
		{
			this.key = key;
			this.value = value;
		}
	}

	/**
	 * Класс представляющий дерево
	 *
	 * @author rassoll
	 * @created 01.01.2018
	 * @$Author$
	 * @$Revision$
	 */
	private static class Tree
	{
		private Node root;

		Tree(Node root)
		{
			this.root = root;
		}

		/**
		 * @return корневой элемент дерева
		 */
		Node getRoot()
		{
			return root;
		}
	}
}