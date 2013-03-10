package edu.tai.interpreter;

import java.util.ArrayList;
import java.util.List;

import edu.tai.interpreter.inst.Instruction;

public class InstructionSequence 
{
	private List<Instruction> instructions;
	
	public InstructionSequence() 
	{
		instructions = new ArrayList<Instruction>();
	}
	
	public void addInstruction(Instruction inst)
	{
		instructions.add(inst);
	}
	
	public Instruction getInstruction(int index)
	{
		return instructions.get(index);
	}
	
	public int getInstructionOperand(int index)
	{
		return getInstruction(index).getOperand();
	}
	
	public void setInstructionAtIndex(int index, Instruction inst)
	{
		instructions.set(index, inst);
	}
}
