package edu.tai.interpreter.inst;

import edu.tai.interpreter.State;

public abstract class Instruction 
{
	protected int operand;
	
	public abstract State execute(State state) throws InstructionException;
	
	public int getOperand()
	{
		return operand;
	}
	
	public class InstructionException extends Exception
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public InstructionException(String errorMessage)
		{
			super(errorMessage);
		}
	}
}
