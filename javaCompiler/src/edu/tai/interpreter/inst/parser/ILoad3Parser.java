package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Iload3;
import edu.tai.interpreter.inst.Instruction;

public class ILoad3Parser implements InstructionParser 
{

	public ILoad3Parser() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		return new Iload3();
	}

}
