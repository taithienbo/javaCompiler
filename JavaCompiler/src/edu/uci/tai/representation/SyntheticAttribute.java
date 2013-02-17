package edu.uci.tai.representation;

import java.io.FileInputStream;

public class SyntheticAttribute extends Attribute {

	public SyntheticAttribute(FileInputStream fis) {
		super(fis);
		setName("Synthetic");
	}

}
