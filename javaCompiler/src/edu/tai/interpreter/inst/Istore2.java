package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class Istore2 extends Instruction
{

	public Istore2() 
	{
		operand = OpCodes.ISTORE_2;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int value = state.popFromStack();
		state.setVarValueAtIndex(2, value);
		return state;
	}

}
