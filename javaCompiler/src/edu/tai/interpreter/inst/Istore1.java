package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class Istore1 extends Instruction
{

	public Istore1() 
	{
		operand = OpCodes.ISTORE_1;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int value = state.popFromStack();
		state.setVarValueAtIndex(1, value);
		return state;
	}

}
