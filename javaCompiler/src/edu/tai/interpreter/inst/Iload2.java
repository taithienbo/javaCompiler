package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class Iload2 extends Instruction
{

	
	public Iload2() 
	{
		operand = OpCodes.ILOAD_2;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int value = state.getVarValueAtIndex(2);
		state.pushToStack(value);
		return state;
	}

}
