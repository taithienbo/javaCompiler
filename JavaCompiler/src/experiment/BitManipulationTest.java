package experiment;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BitManipulationTest 
{
	private BitManipulation bitMan;
	
	
	@Before
	public void setUp() throws Exception 
	{
		bitMan = new BitManipulation();
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	@Test
	public void testGetBit() 
	{
		int number = 33;
		
		assertTrue(bitMan.getBit(number, 0));
		for (int i = 1; i < 5; i++)
			assertFalse(bitMan.getBit(number, i));
		
		assertTrue(bitMan.getBit(number, 5));
		
		number = 32;
		for (int i = 0; i < 8; i++)
		{
			if (i != 5)
				assertFalse(bitMan.getBit(number, i));
			else
				assertTrue(bitMan.getBit(number, i));
		}
		number = 1024;
		assertTrue(bitMan.getBit(number, 10));
		
		for (int i = 0; i < 10; i++)
			assertFalse(bitMan.getBit(number, i));
		
		 number = 18;
		 assertTrue(bitMan.getBit(number, 1));
		 assertTrue(bitMan.getBit(number, 4));
		 assertFalse(bitMan.getBit(number, 5));
		
	}
	
	@Test
	public void testShift1ByBits()
	{
		assertEquals(1, bitMan.leftShift1ByiBits(0));
		assertEquals(2, bitMan.leftShift1ByiBits(1));
		assertEquals(4, bitMan.leftShift1ByiBits(2));
	}
	
	@Test
	public void testintFromBit()
	{
		int bitPos = 9;
		int expectedA = 512;
		
		assertEquals(expectedA, (int) bitMan.valueFromBit(bitPos));
	}
	
	@Test
	public void testintFromBitWithStartPos()
	{
		// 00000001
		int startPos = 7;
		int number = 1;
		int expected = 1;
		assertEquals(expected, (int) bitMan.valueFromBits(number, startPos));
		
		System.out.println();
		
		// 00000010
	    startPos = 15;
	    number = 2;
	    expected = 512;
		assertEquals(expected, (int) bitMan.valueFromBits(number, startPos));
		
		System.out.println();
		
	    startPos = 23;
	    number = 18;
	    expected = 1179648;
		assertEquals(expected, (int) bitMan.valueFromBits(number, startPos));
		
	
	}

}
