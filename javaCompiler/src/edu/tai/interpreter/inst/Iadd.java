package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class Iadd extends Instruction
{

	public Iadd() 
	{
		operand = OpCodes.IADD;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int value1 = state.popFromStack();
		int value2 = state.popFromStack();
		state.pushToStack(value1 + value2);
		return state;
	}

}
