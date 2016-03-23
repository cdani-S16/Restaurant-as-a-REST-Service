package delectable.pojo;

import static org.junit.Assert.*;

import org.junit.Test;

public class MenuItemTesting {

	@Test
	public void test() {
		
		//Signature of the testing constructor
		//public MenuItem(String name, float price,int minQty, int type )
		OldMenuItem testMenuItem = new OldMenuItem("John", 45, 2, 2);
		//assertTrue();
	
	}
	@Test(expected=IllegalArgumentException.class)
	public void testArgumentInvalidException() {
		OldMenuItem testMenuItem = new OldMenuItem("John", 45, 2, 6);
	}
}
