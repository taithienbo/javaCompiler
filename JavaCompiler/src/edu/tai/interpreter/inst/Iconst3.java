package edu.tai.interpreter.inst;

import edu.tai.interpreter.OpCodes;

public class Iconst3 extends Iconst
{

	public Iconst3() 
	{
		super(3);
		operand = OpCodes.ICONST_3;
	}

}
