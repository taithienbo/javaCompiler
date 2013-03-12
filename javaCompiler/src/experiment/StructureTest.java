package experiment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uci.tai.constantPool.Structure;

public class StructureTest 
{


	@Before
	public void setUp() throws Exception 
	{
		
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	@Test
	public void test() 
	{
		byte[] bytes = new byte[2];
		
		byte b1 = Byte.valueOf("00000001");
		byte b2 = Byte.valueOf("00000101");
		int expected = 517;
		bytes[0] = b1;
		bytes[1] = b2;
		
		assertEquals(expected, Structure.valueFromBytes(bytes));
		
		fail("Not yet implemented");
	}

}
