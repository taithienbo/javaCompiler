package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Iload;
import edu.tai.interpreter.inst.Instruction;

public class ILoadParser implements InstructionParser
{

	public ILoadParser() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		int index = seq.getInstructionOperand(state.getIndex() + 1);
		
		return new Iload(index);
	}

}
