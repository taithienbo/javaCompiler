package edu.tai.interpreter.inst;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;

public class IfEq extends Instruction 
{
	private int branchByte1;
	private int branchByte2;
	
	@Inject
	public IfEq(@Named("branchByte1") int branchByte1,
			@Named("branchByte2") int branchByte2) 
	{
		operand = OpCodes.IFEQ;
		this.branchByte1 = branchByte1;
		this.branchByte2 = branchByte2;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int value = state.popFromStack();
		
		if (value == 0)
		{
			int offset = ((byte) branchByte1) << 8 | branchByte2;
			int realOffSet = offset & 0xFF;
			state.setIndex(realOffSet + state.getIndex() -1);
		}
			return state;
	}

}
