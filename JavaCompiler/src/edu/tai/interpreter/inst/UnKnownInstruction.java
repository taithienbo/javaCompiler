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
		System.out.println(String.format("Unimplemented instruction which has " 
				+ "operand: %d", operand));
		return state;
	}
	

}
