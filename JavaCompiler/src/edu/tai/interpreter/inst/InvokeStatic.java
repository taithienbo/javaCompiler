package edu.tai.interpreter.inst;

import java.io.IOException;

import edu.tai.interpreter.State;
import edu.uci.tai.constantPool.ConstantMethodref;
import edu.uci.tai.constantPool.ConstantNameAndType;
import edu.uci.tai.constantPool.ConstantUtf8;
import edu.uci.tai.constantPool.Structure;
import edu.uci.tai.parser.Main;

public class InvokeStatic extends Instruction
{
	private int byteIndex1;
	private int byteIndex2;
	
	public InvokeStatic(int byteIndex1, int byteIndex2) 
	{
		this.byteIndex1 = byteIndex1;
		this.byteIndex2 = byteIndex2;
	}

	@Override
	public State execute(State state) throws InstructionException 
	{
		int targetMethodIndex = byteIndex1 << 8 | byteIndex2;
		
		
		System.out.println(String.format("inst.InvokeStatic" +
				".execute() targetMethodIndex: %d", targetMethodIndex));
	
			try 
			{
				String targetMethodName = getMethodName(targetMethodIndex);
				if (targetMethodName.equals("printInt"))
				{
					System.out.println(String.format
						("%s: found printInt method", getClass().getName()));
					int arg = state.popFromStack();
					System.out.println
						(String.format("%s: arg detected: %d", 
								getClass().getName(), arg));
				}
					
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return state;
	}
	
	private String getMethodName(int methodRefIndex) throws IOException
	{
		Structure structure = Main.constantPool.getStructure(
				(methodRefIndex));
		ConstantMethodref methodRef = (ConstantMethodref) structure;
		ConstantNameAndType nameAndType = (ConstantNameAndType) 
				Main.constantPool
				.getStructure(methodRef.nameAndTypeIndex());
		ConstantUtf8 constantUtf8 = (ConstantUtf8) 
				Main.constantPool.getStructure(nameAndType.nameIndex());
		return constantUtf8.getName();
	}

}
