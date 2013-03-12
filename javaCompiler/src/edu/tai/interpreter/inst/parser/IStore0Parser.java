package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Instruction;
import edu.tai.interpreter.inst.Istore0;

public class IStore0Parser implements InstructionParser
{

	public IStore0Parser() 
	{
		
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		return new Istore0();
	}

}
