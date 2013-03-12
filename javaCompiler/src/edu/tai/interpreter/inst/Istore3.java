package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class Istore3 extends Instruction
{

	public Istore3() 
	{
		operand = OpCodes.ISTORE_3;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int value = state.popFromStack();
		state.setVarValueAtIndex(3, value);
		return state;
	}

}
