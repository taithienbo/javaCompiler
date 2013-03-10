package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class IfCmpNe extends Instruction
{
	private int branchByte1;
	private int branchByte2;
	
	public IfCmpNe(int branchByte1, int branchByte2) 
	{
		operand = OpCodes.IF_ICMPNE;
		this.branchByte1 = branchByte1;
		this.branchByte2 = branchByte2;
	}

	@Override
	public State execute(State state) throws InstructionException
	{
		int value1 = state.popFromStack();
		int value2 = state.popFromStack();
		
		if (value1 != value2)
		{
			int offset = branchByte1 << 8 | branchByte2;
			state.setIndex(offset + state.getIndex() -1);
		}
			return state;
	}

}
