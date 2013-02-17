package edu.uci.tai.representation;

import java.io.FileInputStream;

public class UnRegcognizeAttribute extends Attribute {

	public UnRegcognizeAttribute(FileInputStream fis) {
		super(fis);
		setName("Unrecognizable name");
	}

}
