package org.stepic.greedyAlgorithms.huffmanCodes;

import java.util.*;
import java.util.stream.Collectors;

/**
 * По данной непустой строке ss длины не более 10^4, состоящей из строчных букв латинского алфавита, постройте оптимальный беспрефиксный код.
 * В первой строке выведите количество различных букв kk, встречающихся в строке, и размер получившейся закодированной строки.
 * В следующих kk строках запишите коды букв в формате "letter: code". В последней строке выведите закодированную строку.
 *
 * @author rassoll
 * @created 03.01.2018
 * @$Author$
 * @$Revision$
 */
class HuffmanEncoding
{
	private static final char emptyNodeChar = 216;
	private static final char leftTurnCharacter = '0';
	private static final char rightTurnCharacter = '1';

	/**
	 * Создание частотной таблицы
	 *
	 * @param message сообщение
	 * @return частотная таблица
	 */
	private static HashMap<Character, Integer> makeFrequencyTable(String message)
	{
		LinkedHashMap<Character, Integer> frequencyTable = new LinkedHashMap<>();
		for (char ch : message.toCharArray())
		{
			if (frequencyTable.containsKey(ch))
			{
				frequencyTable.computeIfPresent(ch, (k, v) -> v + 1);
			}
			else
			{
				frequencyTable.put(ch, 1);
			}
		}

		frequencyTable =
			frequencyTable.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		return frequencyTable;
	}

	/**
	 * Создание кодовой таблицы
	 *
	 * @param message сообщение
	 * @return кодовая таблица
	 */
	private static HashMap<Character, String> makeCodeTable(String message)
	{
		Tree huffmanTree = makeTree(message);
		HashMap<Character, String> codeTable = new LinkedHashMap<>();

		for (char ch : message.toCharArray())
		{
			if (!codeTable.containsKey(ch))
			{
				codeTable.put(ch, getBinaryCharacterCode(huffmanTree.getRoot(), ch, new StringBuilder()));
			}
		}

		return codeTable;
	}

	/**
	 * Кодирование сообщения в двоичную форму
	 *
	 * @param message сообщение
	 * @return закодированное сообщение
	 */
	private static String encode(String message)
	{
		Tree huffmanTree = makeTree(message);
		StringBuilder encodedMessage = new StringBuilder();

		for (char ch : message.toCharArray())
		{
			encodedMessage.append(getBinaryCharacterCode(huffmanTree.getRoot(), ch, new StringBuilder()));
		}

		return encodedMessage.toString();
	}

	/**
	 * Построить дерево Хаффмана
	 *
	 * @param message сообщение для постройки дерева
	 * @return дерево Хаффмана
	 */
	private static Tree makeTree(String message)
	{
		PriorityQueue<Tree> trees = new PriorityQueue<>(
			(Tree firstTree, Tree secondTree) ->
			{
				if (firstTree.getRoot().value > secondTree.getRoot().value)
				{
					return 1;
				}
				else if (firstTree.getRoot().value < secondTree.getRoot().value)
				{
					return -1;
				}
				else
				{
					return 0;
				}
			});

		makeFrequencyTable(message)
			.entrySet()
			.forEach((Map.Entry<Character, Integer> entry) ->
			{
				Tree tempTree = new Tree(new Node(entry.getKey(), entry.getValue()));
				trees.add(tempTree);
			});

		if (trees.size() == 1)
		{
			Node leftNode = trees.poll().getRoot();
			Node newNode = new Node(emptyNodeChar, leftNode.value);
			newNode.leftChild = leftNode;
			trees.add(new Tree(newNode));
		}
		else
		{
			while (trees.size() != 1)
			{
				Node leftNode = trees.poll().getRoot();
				Node rightNode = trees.poll().getRoot();

				Node newNode = new Node(emptyNodeChar, leftNode.value + rightNode.value);
				newNode.leftChild = leftNode;
				newNode.rightChild = rightNode;
				trees.add(new Tree(newNode));
			}
		}

		return trees.poll();
	}

	/**
	 * Рекурсивное получение двоичного кода для символа
	 * при помощи дерева Хаффмана
	 *
	 * @param rootNode               корневой элемент дерева Хаффмана
	 * @param character              символ
	 * @param binaryCharacterBuilder постротель двоичного кода
	 * @return двоичный код символа
	 */
	private static String getBinaryCharacterCode(Node rootNode, char character, StringBuilder binaryCharacterBuilder)
	{
		String result = "";

		if (binaryCharacterBuilder == null)
		{
			binaryCharacterBuilder = new StringBuilder();
		}

		if (rootNode.key == (int) character)
		{
			return binaryCharacterBuilder.toString();
		}

		if (rootNode.leftChild != null)
		{
			result = getBinaryCharacterCode(rootNode.leftChild, character, binaryCharacterBuilder.append(leftTurnCharacter));
			if (result != null && !result.equals(""))
			{
				return result;
			}
		}

		if (rootNode.rightChild != null)
		{
			result = getBinaryCharacterCode(rootNode.rightChild, character, binaryCharacterBuilder.append(rightTurnCharacter));
			if (result != null && !result.equals(""))
			{
				return result;
			}
		}

		binaryCharacterBuilder.deleteCharAt(binaryCharacterBuilder.length() - 1);
		return result;
	}

	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		String message = s.next().toLowerCase();

		HashMap<Character, Integer> frequencyTable = makeFrequencyTable(message);
		HashMap<Character, String> codeTable = makeCodeTable(message);
		String encodedMessage = HuffmanEncoding.encode(message);

		System.out.println(frequencyTable.size() + " " + encodedMessage.length());
		codeTable.forEach((key, value) -> System.out.println(key + ": " + value));
		System.out.println(encodedMessage);
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