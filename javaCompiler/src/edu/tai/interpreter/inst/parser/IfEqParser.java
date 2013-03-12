package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.IfEq;
import edu.tai.interpreter.inst.Instruction;

public class IfEqParser implements InstructionParser
{

	public IfEqParser() 
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
		
		return new IfEq(branchByte1, branchByte2);
	}

}
