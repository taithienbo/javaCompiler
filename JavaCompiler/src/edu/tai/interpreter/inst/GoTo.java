package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class GoTo extends Instruction
{

	private int branchByte1;
	private int branchByte2;
	
	public GoTo(int branchByte1, int branchByte2) 
	{
		operand = OpCodes.GOTO;
		this.branchByte1 = branchByte1;
		this.branchByte2 = branchByte2;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int offset = branchByte1 << 8 | branchByte2;
		state.setIndex(state.getIndex() + offset);
		return state;
	}

}
