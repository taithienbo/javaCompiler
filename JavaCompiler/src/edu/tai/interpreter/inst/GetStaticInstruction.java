package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;
import edu.tai.interpreter.State;
import edu.uci.tai.constantPool.ConstantFieldRef;
import edu.uci.tai.constantPool.Structure;

public class GetStaticInstruction extends Instruction
{
	private int constPoolIndex;

	public GetStaticInstruction(int constPoolIndex) 
	{
		operand = OpCodes.GETSTATIC;
		this.constPoolIndex = constPoolIndex;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		ConstantFieldRef consFieldRef;
		Structure structure = state
				.getStructureFromConstantPool(constPoolIndex);
		try
		{
			consFieldRef = (ConstantFieldRef) structure;
			System.out.println(String.format("GetStaticInstruction, " +
					"consFieldRef retrieved: %s", consFieldRef.toString()));
		}
		catch (ClassCastException e)
		{
			if (structure != null)
				throw new InstructionException
				(
					String.format("Failed to retrieve field value \n" +
							"Structure at index: %d has type: %s not " +
							"type %s .", 
							constPoolIndex,
							structure.getClass().getName(),
							ConstantFieldRef.class.getName()
							));
			else
				throw new InstructionException
				(
					String.format("Failed to retrieve field value \n" +
							"No structure could be found in ConstantPool using " 
						+ 	"index: %d .", 
							constPoolIndex));
		}
		
		return state;
	}



}
