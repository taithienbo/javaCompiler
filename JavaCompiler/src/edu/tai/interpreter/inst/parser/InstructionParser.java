package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.Instruction;

public interface InstructionParser 
{
	public Instruction parseInstruction(InstructionSequence seq, State state);
}
