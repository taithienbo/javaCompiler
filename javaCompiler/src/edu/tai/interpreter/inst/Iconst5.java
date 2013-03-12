package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;

public class Iconst5 extends Iconst
{

	public Iconst5() 
	{
		super(5);
		operand = OpCodes.ICONST_5;
	}

}
