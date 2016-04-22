package delectable.test.logic;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import delectable.dto.CustomerDTO;
import delectable.dto.PersonalInfoDTO;
import delectable.logic.CustomerManager;

public class CustomerManagerTest {


	
	@Test
	public void testGetAllCustomers() throws IllegalAccessException, InvocationTargetException {
		List<CustomerDTO> cusGetALL = new ArrayList<CustomerDTO>();
		cusGetALL = CustomerManager.cusMan.getAllCustomers();
		assertEquals(cusGetALL.size(),0);
	}

	@Test
	public void testGetAllCustomersMatching() throws IllegalAccessException, InvocationTargetException {
		List<CustomerDTO> cusGetALL = new ArrayList<CustomerDTO>();
		cusGetALL = CustomerManager.cusMan.getAllCustomersMatching("test");
		assertEquals(cusGetALL.size(),0);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetCustomer() throws IllegalAccessException, InvocationTargetException {
		CustomerDTO cusGetALL = new CustomerDTO();
		cusGetALL = CustomerManager.cusMan.getCustomer(1);
		
	}


}
