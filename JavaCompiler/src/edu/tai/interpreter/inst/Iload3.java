package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class Iload3 extends Instruction
{

	
	public Iload3() 
	{
		operand = OpCodes.ILOAD_3;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int value = state.getVarValueAtIndex(3);
		state.pushToStack(value);
		return state;
	}

}
