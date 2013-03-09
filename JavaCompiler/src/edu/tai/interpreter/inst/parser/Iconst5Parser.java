package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Iconst5;
import edu.tai.interpreter.inst.Instruction;

public class Iconst5Parser implements InstructionParser
{

	public Iconst5Parser() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		// TODO Auto-generated method stub
		return new Iconst5();
	}

}
