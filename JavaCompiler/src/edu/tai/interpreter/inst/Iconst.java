package edu.tai.interpreter.inst;

import edu.tai.interpreter.State;

public class Iconst extends Instruction
{
	private int value;
	
	public Iconst(int value)
	{
		this.value = value;
	}

	@Override
	public State execute(State currentState) 
	{
		currentState.pushToStack(value);
		return currentState;
	}

	@Override
	public int getOperand() 
	{
		// TODO Auto-generated method stub
		return operand;
	}

}
