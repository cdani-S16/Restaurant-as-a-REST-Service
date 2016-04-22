package delectable.test.logic;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import delectable.dto.CustomerDTO;
import delectable.dto.CustomerDetailDTO;
import delectable.dto.MenuItemDTO;
import delectable.dto.OrderDTO;
import delectable.dto.OrderDetailMenuDTO;
import delectable.dto.PersonalInfoDTO;
import delectable.logic.CustomerManager;
import delectable.logic.MenuManager;
import delectable.logic.OrderManager;

public class CustomerManagerTest2 {
	
	PersonalInfoDTO pi;
	@Before
	public void initNewCust()
	{
		pi = new PersonalInfoDTO();
		pi.setEmail("test@email.com");
		pi.setName("John Doe");
		pi.setPhone("+12912312312312");
	}
	
	@Test
	public void testAddCustomer() throws IllegalAccessException, InvocationTargetException {
		CustomerManager.getCusMan().addCustomer(pi);
		//assertEquals(1,1);
	}
	
	@Test
	public void testGetAllCustomersAfterAdd() throws Exception {
		List<CustomerDTO> cusGetALL = new ArrayList<CustomerDTO>();
		cusGetALL = CustomerManager.getCusMan().getAllCustomers();
		assertEquals(cusGetALL.size(),1);
		CustomerDetailDTO cDetDTO; 
		cDetDTO = CustomerManager.getCusMan().getCustomer(0);
		assertEquals(cDetDTO.getEmail().equals("test@email.com"),true);
		
		 List<CustomerDTO> custList = CustomerManager.getCusMan().getAllCustomersMatching("test");
		 assertEquals(custList.size(),1);
		 custList = CustomerManager.getCusMan().getAllCustomersMatching("John");
		 assertEquals(custList.size(),1);
		 custList = CustomerManager.getCusMan().getAllCustomersMatching("Random");
		 assertEquals(custList.size(),0);
		 
		 //adding order and testing if it appeared in customers
		 //adding menu required by the order
			MenuItemDTO tempMenuItem = new MenuItemDTO();
			tempMenuItem.setMinimum_order(2);
			tempMenuItem.setPrice_per_person(2.5f);
			MenuManager.getMenu().AddItem(tempMenuItem);
			//adding order
			OrderDTO ordToADD = new OrderDTO();
			ordToADD.setDelivery_address("sample address");
			ordToADD.setDelivery_date("20160424");
			//sample personal info
			PersonalInfoDTO pi = new PersonalInfoDTO(); 
			pi = new PersonalInfoDTO();
			pi.setEmail("addingcustomer@throughanorder.com");
			pi.setName("Cust Order");
			pi.setPhone("+1264511612");
			ordToADD.setPersonal_info(pi);
			List<OrderDetailMenuDTO> tempOrderList = new ArrayList<OrderDetailMenuDTO>();
			OrderDetailMenuDTO item = new OrderDetailMenuDTO();
			item.setId(0);
			item.setCount(2);
			tempOrderList.add(item);
			ordToADD.setOrder_detail(tempOrderList);
			OrderManager.getOrderMan().addOrder(ordToADD);
		 custList = CustomerManager.getCusMan().getAllCustomersMatching("order");
		 assertEquals(custList.size(),1);
	}


}
