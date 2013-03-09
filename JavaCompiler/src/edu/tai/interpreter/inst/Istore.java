package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class Istore extends Instruction
{
	private int index;
	
	public Istore(int index) 
	{
		operand = OpCodes.ISTORE;
		this.index = index;
	}

	@Override
	public State execute(State state) 
	{
		// TODO Auto-generated method stub
		int value = state.popFromStack();
		state.setVarValueAtIndex(index, value);
		return state;
	}

}
