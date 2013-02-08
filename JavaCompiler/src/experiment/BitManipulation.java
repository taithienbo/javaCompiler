package experiment;

public class BitManipulation 
{
	public static final int NUM_BITS = 8;
	
	public boolean getBit(int number, int i)
	{
		// return the bit value at index i of number which represents a byte
		return (number & leftShift1ByiBits(i)) != 0;
	}
	
	public int leftShift1ByiBits(int shiftAmount)
	{
		return 1 << shiftAmount;
	}
	
	public int intFromBit(int bitPosition)
	{
		return (int) Math.pow(2, bitPosition);
	}
	
	public int intFromBits(int number, int mostSignificantBitPos)
	{
		int result = 0; 
		int pos = mostSignificantBitPos;
		for (int i = NUM_BITS - 1; i >= 0; i--)
		{
			if (getBit(number, i))		
				result += intFromBit(pos);
	
			pos--;
		}
		
		return result;
	}
}
