package edu.uci.tai.javaCompiler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.ConstantPool;


public class Main 
{
	private String fileName;
	private ClassFileRepresentation javaClass;
	
	public Main(String fileName)
	{
		this.fileName = fileName;
		
	}
	
	public static void main(String[] args) throws IOException
	{
		Main parser = new Main("aaa.class");
		parser.parse();
	}
	
	public void parse() throws IOException
	{
		FileInputStream fis = new FileInputStream(new File(fileName));
	
	//	printOutRaw(fis);
		printOutFormatted(fis);

	}
	
	public void printOutRaw(FileInputStream fis) throws IOException
	{
		while (fis.available() > 0)
		{
			System.out.println(fis.read());
		}
	}
	
	public void printOutFormatted(FileInputStream fis) throws IOException
	{
		MagicNumber mg = new MagicNumber(fis);
		VersionNumber vn = new VersionNumber(fis);
		ConstantPool cp = new ConstantPool(fis);
		System.out.println(cp);
		AccessFlag acessFlag = new AccessFlag(fis);
		System.out.println(acessFlag);
	
		// flag
		while (fis.available() > 0)
			System.out.println(fis.read());
	}
}