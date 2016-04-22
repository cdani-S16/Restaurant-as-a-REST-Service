package delectable.test.logic;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import delectable.dto.MenuItemDTO;
import delectable.dto.OrderDTO;
import delectable.dto.OrderDetailDTO;
import delectable.dto.OrderDetailMenuDTO;
import delectable.dto.OrderMiniDTO;
import delectable.dto.PersonalInfoDTO;
import delectable.logic.MenuManager;
import delectable.logic.OrderManager;

public class OrderManagerTest {

	@Test
	public void testGetAllOrders() throws IllegalAccessException, InvocationTargetException {
		//should be an empty order list
		List<OrderMiniDTO> allOrders = new ArrayList<OrderMiniDTO>();
		allOrders = OrderManager.getOrderMan().getAllOrders();
		assertEquals(allOrders.size(),0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetOrder() throws IllegalAccessException, InvocationTargetException {
		//supposed to throw an exception
		OrderManager.getOrderMan().getOrder(0);
	}



}
