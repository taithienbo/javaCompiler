package edu.tai.interpreter.inst.test;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class Test3Test {

	@Test
	public void test() 
	{

		
		Stack<Integer> stack = new Stack<Integer>();

		
		System.out.println(stack.toString());
		stack.push(1);
		assertEquals(1, stack.size());
		System.out.println(stack.toString());
	}

}
