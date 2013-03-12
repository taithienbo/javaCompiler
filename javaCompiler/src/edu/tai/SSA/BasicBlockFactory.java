package edu.tai.SSA;


import java.util.LinkedHashMap;
import java.util.Map;

import com.google.inject.name.Named;

import edu.tai.interpreter.OpCodes;

public class BasicBlockFactory implements AbstractBasicBlockFactory
{

	@Override
	public Map<Integer, BasicBlock> generateBasicBlocks(@Named("operands") 
	int[] operands) 
	{
		Map<Integer, BasicBlock> basicBlockMap = 
							new LinkedHashMap<Integer, BasicBlock>();
		
		basicBlockMap.put(0, new BasicBlock(0));

		for (int i = 0; i < operands.length; i++)
		{
			switch (operands[i])
			{
			case OpCodes.IFEQ:
			case OpCodes.IFNE:
			case OpCodes.IFLT:
			case OpCodes.IFGT:
			case OpCodes.IFLE:				
			case OpCodes.IF_ICMPEQ:
			case OpCodes.IF_ICMPNE:
			case OpCodes.IF_ICMPLT:
			case OpCodes.IF_ICMPGE:
			case OpCodes.IF_ICMPGT:
			case OpCodes.IF_ICMPLE:
			case OpCodes.GOTO:
			{
				int blockStartPc = i + getOffSetFromBranchBytes
						(operands[i+1], operands[i+2]);
				basicBlockMap.put(blockStartPc, 
						new BasicBlock(blockStartPc));
				break;
			}
			case OpCodes.INVOKESTATIC:
				basicBlockMap.put(i, new BasicBlock(i));
				break;	
			default:
				break;
			}
		}
		return basicBlockMap;
	}

	private int getOffSetFromBranchBytes(int branchByte1, int branchByte2)
	{
		return (((byte) branchByte1) << 8 | branchByte2) & 0xFF;
	}



}
