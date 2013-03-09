package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Instruction;
import edu.tai.interpreter.inst.Istore3;

public class IStore3Parser implements InstructionParser
{

	public IStore3Parser() 
	{
		
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		return new Istore3();
	}

}
