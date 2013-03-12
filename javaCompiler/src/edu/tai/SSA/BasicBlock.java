package edu.tai.SSA;

import java.util.ArrayList;
import java.util.List;

public class BasicBlock 
{
	private int startPc;
	List<SSAInstruction> instructions;
	
	List<BasicBlock> sucessors;
	List<BasicBlock> predecessors;
	
	public BasicBlock(int startPc)
	{
		this.startPc = startPc;
		sucessors = new ArrayList<BasicBlock>();
		predecessors = new ArrayList<BasicBlock>();
		instructions = new ArrayList<SSAInstruction>();
	}
	
	public void addSuccessor(BasicBlock successor)
	{
		sucessors.add(successor);
	}
	
	public void addPredecessor(BasicBlock predecessor)
	{
		predecessors.add(predecessor);
	}
	
	@Override
	public String toString()
	{
		return String.format("start_pc: %d", startPc);
	}
	

}
