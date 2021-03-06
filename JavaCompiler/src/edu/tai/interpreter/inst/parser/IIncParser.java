package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.IInc;
import edu.tai.interpreter.inst.Instruction;



public class IIncParser implements InstructionParser
{

	public IIncParser() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		int index = seq.getInstructionOperand(state.getIndex() + 1);
		int constant = seq.getInstructionOperand(state.getIndex() + 2);
		
		return new IInc(index, constant);
	}

}
