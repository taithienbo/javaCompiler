package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Iadd;
import edu.tai.interpreter.inst.Instruction;

public class IAddParser implements InstructionParser
{

	public IAddParser() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		return new Iadd();
	}

}
