package org.stepic.searchTrees;

import java.util.Scanner;

public class Main
{
	public static String FOUND = "Found";
	public static String NOT_FOUND = "Not found";

	public static void main(String[] args)
	{
		try (Scanner scanner = new Scanner(System.in))
		{
			int operationsCount = scanner.nextInt();
			AVLTree avlTree = new AVLTree();
			for (int i = 0; i < operationsCount; i++)
			{
				String operation = scanner.next();
				if (operation.startsWith("+"))
				{
					avlTree.root = avlTree.add(avlTree.root, scanner.nextInt());
				}
				else if (operation.startsWith("-"))
				{
					avlTree.root = avlTree.deleteNode(avlTree.root, scanner.nextInt());
				}
				else if (operation.startsWith("?"))
				{
					System.out.println(avlTree.find(scanner.nextInt()) == null ? NOT_FOUND : FOUND);
				}
				else if (operation.startsWith("s"))
				{
					System.out.println(scanner.nextInt() + scanner.nextInt());
				}
			}
		}
	}

	private static class Node
	{
		int key;
		int height;

		Node left;
		Node right;

		Node(int d)
		{
			key = d;
			height = 1;
		}
	}

	private static class AVLTree
	{
		Node root;

		/**
		 * @param node ссылка на узел
		 * @return высота поддерва
		 */
		private int height(Node node)
		{
			return node == null ? 0 : node.height;
		}

		// A utility function to right rotate subtree rooted with y
		// See the diagram given above.

		/**
		 * Выполнить правый поворот поддерва с корнем в
		 *
		 * @param subTreeRoot корень поддерева
		 * @return новый корень
		 */
		private Node rightRotate(Node subTreeRoot)
		{
			Node left = subTreeRoot.left;
			Node rightChildOfLeft = left.right;

			//выполним поворот
			left.right = subTreeRoot;
			subTreeRoot.left = rightChildOfLeft;

			//обновим высоты
			subTreeRoot.height = Math.max(height(subTreeRoot.left), height(subTreeRoot.right)) + 1;
			left.height = Math.max(height(left.left), height(left.right)) + 1;

			//вернем новый корень
			return left;
		}

		/**
		 * Выполнить левый поворот поддерва с корнем в subTreeRoot
		 *
		 * @param subTreeRoot корень поддерева
		 * @return новый корень
		 */
		private Node leftRotate(Node subTreeRoot)
		{
			Node right = subTreeRoot.right;
			Node leftChildOfRight = right.left;

			//выполним поворот
			right.left = subTreeRoot;
			subTreeRoot.right = leftChildOfRight;

			//обновим высоты
			subTreeRoot.height = Math.max(height(subTreeRoot.left), height(subTreeRoot.right)) + 1;
			right.height = Math.max(height(right.left), height(right.right)) + 1;

			//вернем новый корень
			return right;
		}

		/**
		 * Получить разницу высот в поддереве с корнем в node
		 *
		 * @param node узел
		 * @return разница высот поддеревьев узла
		 */
		private int getHeightDifference(Node node)
		{
			return node == null ? 0 : height(node.left) - height(node.right);
		}

		/**
		 * Выполнить добавление узла в дерево
		 *
		 * @param node корень дерева
		 * @param key  значение для нового узла
		 * @return корень дерева
		 */
		Node add(Node node, int key)
		{
			//выполним просто поворот
			if (node == null)
			{
				return (new Node(key));
			}

			if (key < node.key)
			{
				node.left = add(node.left, key);
			}
			else if (key > node.key)
			{
				node.right = add(node.right, key);
			}
			//одинаковые значение не всталяем
			else
			{
				return node;
			}

			//обновим высоту узла
			node.height = 1 + Math.max(height(node.left), height(node.right));

			//получим разницу высот
			int heightDifference = getHeightDifference(node);

			//дерево несбалансированно
			//левый левый
			if (heightDifference > 1 && key < node.left.key)
			{
				return rightRotate(node);
			}

			//правый правый
			if (heightDifference < -1 && key > node.right.key)
			{
				return leftRotate(node);
			}

			//левый правый
			if (heightDifference > 1 && key > node.left.key)
			{
				node.left = leftRotate(node.left);
				return rightRotate(node);
			}

			//правый левый
			if (heightDifference < -1 && key < node.right.key)
			{
				node.right = rightRotate(node.right);
				return leftRotate(node);
			}

			return node;
		}

		/**
		 * Найти минимальный существующий узел в поддереве с корнем в node
		 *
		 * @param node корень поддерева
		 * @return минимальный существующий узел
		 */
		private Node minValueNode(Node node)
		{
			Node current = node;

			//передвигаемся к крайнему левому листу
			while (current.left != null)
			{
				current = current.left;
			}

			return current;
		}

		/**
		 * Выполнить удаление узла
		 *
		 * @param root корень дерева
		 * @param key  значение удаляемого узла
		 * @return корень дерева
		 */
		Node deleteNode(Node root, int key)
		{
			//просто удаление
			if (root == null)
			{
				return root;
			}

			//ключ удаляемого узла меньше левого дочернего, удаление потребуется в левом поддереве
			if (key < root.key)
			{
				root.left = deleteNode(root.left, key);
			}

			//ключ удаляемого узла меньше больше дочернего, удаление потребуется в правом поддереве
			else if (key > root.key)
			{
				root.right = deleteNode(root.right, key);
			}

			//значение текущего узла равно значению удаляемого
			else
			{

				//узел с одним дочерним узлом или без них
				if ((root.left == null) || (root.right == null))
				{
					Node temp = root.left == null ? root.right : root.left;

					//нет дочерних узлов
					if (temp == null)
					{
						root = null;
					}
					//один дочерний узел
					else
					{
						root = temp;
					}
				}
				else
				{
					//узел с двумя дочерними, ищем приемника - минимальный узел
					Node temp = minValueNode(root.right);

					//копируем значение приемника
					root.key = temp.key;

					//удаляем приемника
					root.right = deleteNode(root.right, temp.key);
				}
			}

			//дерево имеет только один узел
			if (root == null)
			{
				return root;
			}

			//обновим высоту текущего узла
			root.height = Math.max(height(root.left), height(root.right)) + 1;

			//получим разницу высот
			int heightDifference = getHeightDifference(root);

			//дерево несбалансированно
			//левый левый
			if (heightDifference > 1 && getHeightDifference(root.left) >= 0)
			{
				return rightRotate(root);
			}

			//левый правый
			if (heightDifference > 1 && getHeightDifference(root.left) < 0)
			{
				root.left = leftRotate(root.left);
				return rightRotate(root);
			}

			//правый правый
			if (heightDifference < -1 && getHeightDifference(root.right) <= 0)
			{
				return leftRotate(root);
			}

			//правый левый
			if (heightDifference < -1 && getHeightDifference(root.right) > 0)
			{
				root.right = rightRotate(root.right);
				return leftRotate(root);
			}

			return root;
		}

		/**
		 * Найти узел с ключом  == key
		 *
		 * @param key ключ
		 * @return найденный узел или null
		 */
		Node find(int key)
		{
			Node currentNode = root;

			while (true)
			{
				if (currentNode == null)
				{
					break;
				}

				if (key > currentNode.key)
				{
					if (currentNode.right == null)
					{
						currentNode = null;
						break;
					}
					else
					{
						currentNode = currentNode.right;
					}
				}
				else if (key < currentNode.key)
				{
					if (currentNode.left == null)
					{
						currentNode = null;
						break;
					}
					else
					{
						currentNode = currentNode.left;
					}
				}
				else
				{
					break;
				}
			}

			return currentNode;
		}
	}
}
