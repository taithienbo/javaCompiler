package edu.tai.interpreter.inst;

import java.io.IOException;
import java.util.ArrayList;

import edu.tai.interpreter.ByteCodeInterpreter;
import edu.tai.interpreter.InstructionParserBuilder.ParserException;
import edu.tai.interpreter.State;
import edu.uci.tai.constantPool.ConstantMethodref;
import edu.uci.tai.constantPool.ConstantNameAndType;
import edu.uci.tai.constantPool.ConstantUtf8;
import edu.uci.tai.constantPool.Structure;
import edu.uci.tai.parser.Main;
import edu.uci.tai.representation.MethodArray.Method;

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
		int targetMethodIndex = ((byte) byteIndex1 << 8) | byteIndex2 ;
		
		ArrayList<Integer> args = new ArrayList<Integer>();

		while (!state.stackIsEmpty())
		{
			args.add(state.popFromStack());
		}

		String targetMethodName = "";
			try 
			{
				targetMethodName = getMethodName(targetMethodIndex);
				if (targetMethodName.equals("printInt"))
				{
					StringBuilder builder = new StringBuilder();
					for (int i = args.size() - 1; i >=0; i--)
						builder.append(args.get(i)).append(", ");
					int commaIndex = builder.lastIndexOf(",");
		
					System.out.println(String.format("arg(s) parsed: %s",
							builder.substring(0, commaIndex)));
				}
				else
				{
					Method targetMethod = Main.methods
							.getMethodByName(targetMethodName);
					ByteCodeInterpreter interpreter = new ByteCodeInterpreter();
					interpreter.interpretMethod(targetMethod, args);
				}
					
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (ParserException e) 
			{
				System.out.println(String.format("An error occured while " +
						"interpreting target method: %s \n %s \n" +
						"%s", targetMethodName,
						e.getMessage()));
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
