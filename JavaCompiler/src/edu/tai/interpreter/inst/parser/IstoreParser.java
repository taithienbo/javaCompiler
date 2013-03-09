package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Instruction;
import edu.tai.interpreter.inst.Istore;

public class IstoreParser implements InstructionParser 
{

	public IstoreParser() 
	{
		
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		int index = seq.getInstruction(state.getIndex() + 1).getOperand();
		return new Istore(index);
	}

}
