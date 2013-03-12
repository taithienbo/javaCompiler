package edu.tai.interpreter.inst.parser;

import edu.tai.interpreter.InstructionSequence;
import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.BiPush;
import edu.tai.interpreter.inst.Instruction;

public class BiPushParser implements InstructionParser
{

	public BiPushParser() 
	{
		
	}

	@Override
	public Instruction parseInstruction(InstructionSequence seq, State state) 
	{
		int valueToPush = seq.getInstructionOperand
							(state.getIndex() + 1);
		return new BiPush(valueToPush);
	}

}
