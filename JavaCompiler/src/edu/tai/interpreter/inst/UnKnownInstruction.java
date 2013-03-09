package edu.tai.interpreter.inst;

import edu.tai.interpreter.State;

public class UnKnownInstruction extends Instruction 
{
	public UnKnownInstruction(int operand)
	{
		this.operand = operand;;
	}

	@Override
	public State execute(State state) 
	{
		throw new IllegalStateException
		(String.format
				("cannot execute unknown instruction. \n Operand: %d", operand));
	}
	

}
