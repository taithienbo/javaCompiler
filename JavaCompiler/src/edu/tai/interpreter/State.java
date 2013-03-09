package edu.tai.interpreter;

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

	public State(int stackSize, int numOfVariables, ConstantPool constPool) 
	{
		stack = new Stack<Integer>();
		stack.setSize(stackSize);
		localVariables = new int[numOfVariables];
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
	
	public int setVarValueAtIndex(int index, int value)
	{
		int oldValue = localVariables[index];
		localVariables[index] = value;
		return oldValue;
	}
	
	public int getVarValueAtIndex(int index)
	{
		return localVariables[index];
	}
	
	public void incrementIndex()
	{
		instructionIndex++;
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
	
}