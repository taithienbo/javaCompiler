package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;

public class Iconst4 extends Iconst
{

	public Iconst4() 
	{
		super(4);
		operand = OpCodes.ICONST_4;
	}

}
