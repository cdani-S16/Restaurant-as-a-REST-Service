package delectable;

import static org.junit.Assert.*;

import org.junit.Test;

public class EntityTesting {

	@Test
	public void test() {
		
		//Signature of the testing constructor
		//public MenuItem(String name, float price,int minQty, int type )
		MenuItem testMenuItem = new MenuItem("John", 45, 2, 2);
		//assertTrue();
	
	}
	@Test(expected=IllegalArgumentException.class)
	public void testArgumentInvalidException() {
		MenuItem testMenuItem = new MenuItem("John", 45, 2, 6);
	}
}
