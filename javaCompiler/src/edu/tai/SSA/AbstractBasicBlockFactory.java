package edu.tai.SSA;

import java.util.Map;

public interface AbstractBasicBlockFactory 
{
	public Map<Integer, BasicBlock> generateBasicBlocks(int[] operands); 
}
