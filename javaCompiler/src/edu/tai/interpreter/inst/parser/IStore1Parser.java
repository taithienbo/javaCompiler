package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Instruction;
import edu.tai.interpreter.inst.Istore1;

public class IStore1Parser implements InstructionParser
{

	public IStore1Parser() 
	{
		
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		return new Istore1();
	}

}
