package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class Istore0 extends Instruction
{

	public Istore0() 
	{
		operand = OpCodes.ISTORE_0;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int value = state.popFromStack();
		state.setVarValueAtIndex(0, value);
		return state;
	}

}
