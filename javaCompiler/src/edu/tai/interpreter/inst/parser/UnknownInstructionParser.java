package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Instruction;
import edu.tai.interpreter.inst.UnKnownInstruction;

public class UnknownInstructionParser implements InstructionParser
{
	private int opCode;
	
	public UnknownInstructionParser(int opCode) 
	{
		this.opCode = opCode;
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		// TODO Auto-generated method stub
		return new UnKnownInstruction(opCode);
	}

}
