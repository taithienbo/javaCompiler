package edu.tai.interpreter.inst;

import edu.tai.interpreter.State;
import edu.uci.tai.constantPool.ConstantMethodref;
import edu.uci.tai.constantPool.Structure;

public class InvokeStatic extends Instruction
{
	private int byteIndex1;
	private int byteIndex2;
	
	public InvokeStatic(int byteIndex1, int byteIndex2) 
	{
		this.byteIndex1 = byteIndex1;
		this.byteIndex2 = byteIndex2;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int targetMethodIndex = byteIndex1 << 8 | byteIndex2;
		
		Structure structure = state.getStructureFromConstantPool
				(targetMethodIndex);
		
		try
		{
			if (structure == null)
				throw new InstructionException
				(String.format("Null Structure at index: %d \n" , 
						targetMethodIndex));
			
			ConstantMethodref methodRef = (ConstantMethodref) structure;
		}
		catch (ClassCastException e)
		{
			throw new InstructionException
			(String.format("Structure at index: %d has type: %s not type: %s\n",
					structure.getClass().getName(),
					ConstantMethodref.class.getName()));
		}
		return state;
	}

}
