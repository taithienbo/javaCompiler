package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class IfCmpEq extends Instruction
{
	private int branchByte1;
	private int branchByte2;
	
	public IfCmpEq(int branchByte1, int branchByte2) 
	{
		operand = OpCodes.IF_ICMPEQ;
		this.branchByte1 = branchByte1;
		this.branchByte2 = branchByte2;
	}

	@Override
	public State execute(State state) throws InstructionException
	{
		int value1 = state.popFromStack();
		int value2 = state.popFromStack();
		
		if (value1 == value2)
		{
			int offset = ((byte) branchByte1) << 8 | branchByte2;
			int realOffSet = offset & 0xFF;
			state.setIndex(realOffSet + state.getIndex() -1);
		}
			return state;
	}

}
