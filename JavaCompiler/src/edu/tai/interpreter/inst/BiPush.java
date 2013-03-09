package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class BiPush extends Instruction
{
	int valueToPush;
	
	public BiPush(int valueToPush) 
	{
		operand = OpCodes.BIPUSH;
		this.valueToPush = valueToPush;
	}

	@Override
	public State execute(State state) throws InstructionException
	{
		state.pushToStack(valueToPush);
		return state;
	}

}
