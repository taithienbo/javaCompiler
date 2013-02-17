package edu.uci.tai.representation;

import java.io.FileInputStream;

public class ConstantValueAttribute extends Attribute {

	public ConstantValueAttribute(FileInputStream fis) {
		super(fis);
		
		setName("ConstantValue");
	}

}
