package edu.tai.interpreter;

public class OpCodes 
{
	public static final int NOP 		=		0;
	public static final int ICONST_0 	=       3;
	public static final int ICONST_1 	=       4;
	public static final int ICONST_2 	=    	5;
	public static final int ICONST_3    =   	6;
	public static final int ICONST_4    =    	7;
	public static final int ICONST_5    =   	8;
	public static final int BIPUSH 		=		16;
	public static final int ILOAD       =    	21;
	public static final int ILOAD_0     =    	26;
	public static final int ILOAD_1     =    	27;
	public static final int ILOAD_2     =  	 	28;
	public static final int ILOAD_3     =    	29;
	public static final int ISTORE      =   	54;
	public static final int ISTORE_0    =    	59;
	public static final int ISTORE_1    =     	60;
	public static final int ISTORE_2    =    	61;
	public static final int ISTORE_3    =    	62;
	public static final int IADD        =     	96;
	public static final int IINC        =     	132;
	public static final int IFEQ        =     	153;
	public static final int IFNE        =    	154;
	public static final int IFLT        =     	155;
	public static final int IFGE        =     	156;
	public static final int IFGT        =     	157;
	public static final int IFLE        =     	158;
	public static final int IF_ICMPEQ   =    	159;
	public static final int IF_ICMPNE   =    	160;
	public static final int IF_ICMPLT   =    	161;
	public static final int IF_ICMPGE   =   	162;
	public static final int IF_ICMPGT   =    	163;
	public static final int IF_ICMPLE   =    	164;
	public static final int GOTO        =     	167;
	public static final int RET         =     	169;
	public static final int INVOKESTATIC =    	184;
	
	
	public OpCodes() 
	{
		// TODO Auto-generated constructor stub
	}

}
