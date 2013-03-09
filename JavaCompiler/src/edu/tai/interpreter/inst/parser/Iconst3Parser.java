package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Iconst3;
import edu.tai.interpreter.inst.Instruction;

public class Iconst3Parser implements InstructionParser 
{

	public Iconst3Parser() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		// TODO Auto-generated method stub
		return new Iconst3();
	}

}
