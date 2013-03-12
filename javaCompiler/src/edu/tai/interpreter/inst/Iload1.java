package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class Iload1 extends Instruction
{

	
	public Iload1() 
	{
		operand = OpCodes.ILOAD_1;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int value = state.getVarValueAtIndex(1);
		state.pushToStack(value);
		return state;
	}

}
