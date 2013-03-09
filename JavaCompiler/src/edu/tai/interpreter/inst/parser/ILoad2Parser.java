package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Iload2;
import edu.tai.interpreter.inst.Instruction;

public class ILoad2Parser implements InstructionParser 
{

	public ILoad2Parser() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		return new Iload2();
	}

}
