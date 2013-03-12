package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Instruction;
import edu.tai.interpreter.inst.InvokeStatic;

public class InvokeStaticParser implements InstructionParser
{

	public InvokeStaticParser() 
	{

	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		int indexByte1 = seq.getInstructionOperand
				(state.getIndex() + 1);
		int indexByte2 = seq.getInstructionOperand
				(state.getIndex() + 2);
		return new InvokeStatic(indexByte1, indexByte2);
	}

}
