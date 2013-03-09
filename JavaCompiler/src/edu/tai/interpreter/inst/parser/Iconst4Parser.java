package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Iconst4;
import edu.tai.interpreter.inst.Instruction;

public class Iconst4Parser implements InstructionParser 
{

	public Iconst4Parser() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		// TODO Auto-generated method stub
		return new Iconst4();
	}

}
