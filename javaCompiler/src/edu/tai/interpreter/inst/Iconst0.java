package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;

public class Iconst0 extends Iconst
{

	public Iconst0() 
	{
		super(0);
		operand = OpCodes.ICONST_0;
	}

}
