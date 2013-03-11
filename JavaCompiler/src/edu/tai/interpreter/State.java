package edu.tai.interpreter;

import java.util.List;
import java.util.Stack;

import com.google.inject.ProvidedBy;

import edu.uci.tai.constantPool.ConstantPool;
import edu.uci.tai.constantPool.Structure;

@ProvidedBy(edu.tai.interpreter.inst.test.IfEqTest.StateProvider.class) 
public class State 
{
	private Stack<Integer> stack;
	private int[] localVariables;
	private int instructionIndex;

	
	private ConstantPool constPool;

	public State(int stackSize, int numOfVariables, List<Integer> args, 
			ConstantPool constPool) 
	{
		stack = new Stack<Integer>();
		localVariables = new int[numOfVariables];

		for (int i = args.size() - 1; i >= 0; i--)
			localVariables[i] = args.get(i);
		
		this.constPool = constPool;
	}
	
	public void pushToStack(int value)
	{
		stack.push(value);
	}
	
	public int popFromStack()
	{
		return stack.pop();
	}
	
	public boolean stackIsEmpty()
	{
		return stack.isEmpty();
	}
	
	
	public int getElementFromStackAtIndex(int index)
	{
		return stack.get(index);
	}
	
	
	public int setVarValueAtIndex(int index, int value)
	{
		int oldValue = localVariables[index];
		localVariables[index] = value;
		return oldValue;
	}
	
	public int localVarSize()
	{
		return localVariables.length;
	}
	
	public int getVarValueAtIndex(int index)
	{
		return localVariables[index];
	}
	
	public int incrementIndex()
	{
		instructionIndex++;
		return instructionIndex;
	}
	
	public void setIndex(int index)
	{
		instructionIndex = index;
	}
	
	public int getIndex()
	{
		return instructionIndex;
	}
	
	public Structure getStructureFromConstantPool(int index)
	{
		return constPool.getStructure(index);
	}
	
	@Override
	public String toString()
	{
		return String.format("Stack: %s", stack.toString());
	}
	
}
