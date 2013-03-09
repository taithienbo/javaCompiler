package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;

public class Iconst2 extends Iconst
{

	public Iconst2(int operand) 
	{
		super(2);
		operand = OpCodes.ICONST_2;
	}

}
