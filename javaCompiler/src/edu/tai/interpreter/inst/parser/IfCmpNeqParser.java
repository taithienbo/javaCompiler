package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.IfCmpNe;
import edu.tai.interpreter.inst.Instruction;

public class IfCmpNeqParser implements InstructionParser
{

	public IfCmpNeqParser() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		int branchByte1 = seq.getInstructionOperand
				(state.getIndex() + 1);
		int branchByte2 = seq.getInstructionOperand
			(state.getIndex() + 2);
		return new IfCmpNe(branchByte1, branchByte2);
	}

}
