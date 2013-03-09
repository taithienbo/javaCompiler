package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class IInc extends Instruction 
{
	private int index;
	private int constant;
	
	public IInc(int index, int constant) 
	{
		operand = OpCodes.IINC;
		this.index = index;
		this.constant = constant;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		// increment local variable #index by signed byte const
		state.setVarValueAtIndex(index, 
			state.getVarValueAtIndex(index) + constant);
		return state;
	}

}
