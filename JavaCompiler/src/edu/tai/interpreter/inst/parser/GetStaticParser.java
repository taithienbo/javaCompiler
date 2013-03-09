package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.GetStaticInstruction;
import edu.tai.interpreter.inst.Instruction;

public class GetStaticParser implements InstructionParser 
{

	public GetStaticParser() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		int index1 = state.getIndex() + 1;
		int index2 = state.getIndex() + 2;
		int constPoolIndex = index1 << 8 | index2;
		
		return new GetStaticInstruction(constPoolIndex);
	}

}
