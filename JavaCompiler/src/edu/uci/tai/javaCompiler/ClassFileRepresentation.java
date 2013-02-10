package edu.uci.tai.javaCompiler;

public class ClassFileRepresentation 
{
	private MagicNumber magicNumber;
	private VersionNumber versionNumber;
	
	public ClassFileRepresentation(MagicNumber magicNumber, 
			VersionNumber versionNumber)
	{
		this.magicNumber = magicNumber;
		this.versionNumber = versionNumber;
	}
	
}
