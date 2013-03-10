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
		System.out.println(String.format("%s: shiftedBranchByte1: %d",
				getClass().getName(), (int) shiftedBranchByte1));
		
		int offset =  shiftedBranchByte1 |  branchByte2;

		byte offsetByte = (byte) offset;
		int signedOffSet =  (int) offsetByte;
		
		System.out.println(String.format("%s: offset is: %d",
				getClass().getName(), (int) offsetByte));
		state.setIndex(state.getIndex() + offsetByte -1);
		return state;
	}

}
