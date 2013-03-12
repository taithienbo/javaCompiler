package edu.tai.interpreter.inst;

import java.util.Stack;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class Iload extends Instruction
{
	private int valueIndex;
	
	public Iload(int valueIndex) 
	{
		operand = OpCodes.ILOAD;
		this.valueIndex = valueIndex;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int value = state.getVarValueAtIndex(valueIndex);
		state.pushToStack(value);
		return state;
	}


}
