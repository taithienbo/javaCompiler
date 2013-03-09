package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class IfCmpLt extends Instruction
{
	private int branchByte1;
	private int branchByte2;
	
	public IfCmpLt(int branchByte1, int branchByte2) 
	{
		operand = OpCodes.IF_ICMPLT;
		this.branchByte1 = branchByte1;
		this.branchByte2 = branchByte2;
	}

	@Override
	public State execute(State state) throws InstructionException
	{
		int value1 = state.popFromStack();
		int value2 = state.popFromStack();
		
		if (value1 < value2)
			state.setIndex(branchByte1 << 8 | branchByte2);
		return state;
	}

}
