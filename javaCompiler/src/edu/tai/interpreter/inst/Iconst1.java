package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;

public class Iconst1 extends Iconst
{

	public Iconst1() 
	{
		super(1);
		operand = OpCodes.ICONST_1;
	}

}
