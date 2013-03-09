package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class Iload0 extends Instruction
{

	
	public Iload0() 
	{
		operand = OpCodes.ILOAD_0;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int value = state.getVarValueAtIndex(0);
		state.pushToStack(value);
		return state;
	}

}
