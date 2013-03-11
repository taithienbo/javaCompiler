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
		byte shiftedBranchByte1 = (byte) (branchByte1 << 8);

		int offset =  shiftedBranchByte1 | branchByte2;

		byte offsetByte = (byte) offset;
		int signedOffSet =  (int) offsetByte;
		int actualIndex = state.getIndex() + signedOffSet;

		state.setIndex(actualIndex -1);

		return state;
	}

}
