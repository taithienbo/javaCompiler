package edu.uci.tai.representation;

import java.io.FileInputStream;

public class LocalVariableTableAttribute extends Attribute {

	public LocalVariableTableAttribute(FileInputStream fis) {
		super(fis);
		setName("LocalVariableTable");
	}

}
