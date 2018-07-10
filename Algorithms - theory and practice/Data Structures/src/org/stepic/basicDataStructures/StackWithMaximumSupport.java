package org.stepic.basicDataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class StackWithMaximumSupport
{
	public static void main(String[] args) throws IOException
	{
		MyReader reader = new MyReader(new InputStreamReader(System.in));
		int operationCount = reader.nextInt();

		Deque<Integer> operationStack = new ArrayDeque<>(operationCount);
		Deque<Integer> maxStack = new ArrayDeque<>(operationCount);
		for (int i = 0; i < operationCount; i++)
		{
			String operation = reader.next();
			if (operation.startsWith("push"))
			{
				int value = reader.nextInt();
				operationStack.push(value);
				maxStack.push(Math.max(maxStack.isEmpty() ? 0 : maxStack.peek(), value));
			}
			else if (operation.startsWith("pop"))
			{
				operationStack.pop();
				maxStack.pop();
			}
			else
			{
				System.out.println(maxStack.isEmpty() ? 0 : maxStack.peek());
			}
		}
	}

	static class MyReader
	{
		private BufferedReader reader = null;
		private StringTokenizer tokenizer = null;

		MyReader(Reader r) throws IOException
		{
			reader = new BufferedReader(r);
		}

		int nextInt() throws IOException
		{
			return Integer.parseInt(next());
		}

		String next() throws IOException
		{
			while (tokenizer == null || !tokenizer.hasMoreTokens())
			{
				tokenizer = new StringTokenizer(reader.readLine());
			}
			return tokenizer.nextToken();
		}
	}
}