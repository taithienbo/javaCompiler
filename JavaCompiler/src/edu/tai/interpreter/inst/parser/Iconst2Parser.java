package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Iconst2;
import edu.tai.interpreter.inst.Instruction;

public class Iconst2Parser implements InstructionParser 
{

	public Iconst2Parser() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		// TODO Auto-generated method stub
		return new Iconst2(OpCodes.ICONST_2);
	}

}
