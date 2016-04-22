package delectable.test.logic;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import delectable.dto.AdminSchrDTO;
import delectable.dto.IdDTO;
import delectable.dto.MenuItemDTO;
import delectable.dto.OrderDTO;
import delectable.dto.OrderDetailMenuDTO;
import delectable.dto.PersonalInfoDTO;
import delectable.dto.ReportAllOrdersDTO;
import delectable.dto.ReportOrderDTO;
import delectable.dto.RevenueReportDTO;
import delectable.logic.MenuManager;
import delectable.logic.OrderManager;
import delectable.logic.ReportManager;
import delectable.pojo.ReportTypes;

public class ReportManagerTest {

	@Test
	public void testGetReportTypes() throws IllegalAccessException, InvocationTargetException {
		int loopLength = ReportManager.reportMan.getReportTypes().size();
		
		for(int i = 0; i < loopLength; i++)
		{
			int curId = ReportManager.reportMan.getReportTypes().get(i).getId();
			if(curId == 800)
				assertEquals(ReportManager.reportMan.getReportTypes().get(i).getName()
					,"Orders to deliver today");
			else if(curId == 801)
				assertEquals(ReportManager.reportMan.getReportTypes().get(i).getName()
						,"Orders to deliver tomorrow");
			else if(curId == 802)
				assertEquals(ReportManager.reportMan.getReportTypes().get(i).getName()
					,"Revenue report");
			else if(curId == 803)
				assertEquals(ReportManager.reportMan.getReportTypes().get(i).getName()
					,"Orders delivery report");
		}
		
		//fail("Not yet implemented");
		
	}

	@Test
	public void testGetRevenueReport() throws Exception {
		//set surcharge
		AdminSchrDTO scr = new AdminSchrDTO();
		scr.setSurcharge(10);
		MenuManager.menu.setSurcharge(scr);
		
		//getting the menu and order ready for the report
		//adding sample menu item
		MenuItemDTO tempMenuItem = new MenuItemDTO();
		tempMenuItem.setMinimum_order(2);
		tempMenuItem.setPrice_per_person(2.5f);
		IdDTO testID = MenuManager.menu.AddItem(tempMenuItem);
		//System.out.println(testID.getId());
		
		MenuItemDTO tempMenuItem2 = new MenuItemDTO();
		tempMenuItem2.setMinimum_order(4);
		tempMenuItem2.setPrice_per_person(3.5f);
		IdDTO testID2 = MenuManager.menu.AddItem(tempMenuItem2);
		//System.out.println(testID2.getId());
		
		//System.out.println(MenuManager.menu.getAllMenuItems().get(1).getMinimum_order());
		
		
		OrderDTO ordToADD = new OrderDTO();
		ordToADD.setDelivery_address("sample address");
		ordToADD.setDelivery_date("20160420");
		//sample personal info
		PersonalInfoDTO pi = new PersonalInfoDTO(); 
		pi.setEmail("random@testemail.com");
		ordToADD.setPersonal_info(pi);
		List<OrderDetailMenuDTO> tempOrderList = new ArrayList<OrderDetailMenuDTO>();
		OrderDetailMenuDTO item = new OrderDetailMenuDTO();
		item.setId(0);
		item.setCount(2);
		tempOrderList.add(item);
		ordToADD.setOrder_detail(tempOrderList);
		//ordToADD.setStatus("open");
		OrderManager.order.addOrder(ordToADD);
		//System.out.println(OrderManager.order.getOrder(0).getAmount());
		//System.out.println(OrderManager.order.getOrder(0).getSurcharge());
		//System.out.println("hi!");
		//System.out.println(OrderManager.order.getOrder(0).getOrder_detail().get(0).getId());
		
		OrderDTO ordToADD2 = new OrderDTO();
		ordToADD2.setDelivery_address("sample address2");
		ordToADD2.setDelivery_date("20160424");
		//sample personal info
		pi = new PersonalInfoDTO(); 
		pi.setEmail("random@testemail2.com");
		ordToADD2.setPersonal_info(pi);
		tempOrderList = new ArrayList<OrderDetailMenuDTO>();
		item = new OrderDetailMenuDTO();
		item.setId(1);
		item.setCount(8);
		tempOrderList.add(item);
		ordToADD2.setOrder_detail(tempOrderList);
		//ordToADD2.setStatus("open");
		OrderManager.order.addOrder(ordToADD2);
		//System.out.println(OrderManager.order.getOrder(1).getAmount());
		//System.out.println(OrderManager.order.getOrder(1).getSurcharge());
		

		
		RevenueReportDTO revRep = new RevenueReportDTO();
		String startDate = "20160420";
		String endDate = "20160425";
		revRep = ReportManager.reportMan.getRevenueReport(startDate, endDate);
		boolean compare = false;
		if(revRep.getFood_revenue() == 33)
			compare = true;
		assertEquals(compare, true);
		if(revRep.getSurcharge_revenue() == 2.8)
			compare = true;
		assertEquals(compare, true);
		
		//System.out.println(revRep.getFood_revenue());
		//System.out.println(revRep.getSurcharge_revenue());
		
		
		ReportOrderDTO repDTO1 = ReportManager.reportMan.getOrderReport(800, "20160420");
		ReportOrderDTO repDTO2 = ReportManager.reportMan.getOrderReport(801, "20160420");
		assertEquals(repDTO1.getOrders().size(),1);
		assertEquals(repDTO2.getOrders().size(),0);
		
		
		ReportAllOrdersDTO repDTO3;// = new ReportAllOrdersDTO();
		startDate = "20160419";
		endDate = "20160425";
		repDTO3 = ReportManager.reportMan.getOrdersReport(startDate, endDate);
		compare = false;
		//System.out.println("  ");
		//System.out.println(repDTO3.getItem_orders().get(0).getCount());
		//System.out.println(repDTO3.getItem_orders().get(1).getCount());
		//System.out.println("sfsfsdfsdf");
		//System.out.println(repDTO3.getItem_orders().size());
		if(repDTO3.getItem_orders().get(0).getCount() == 2)
			compare = true;
		assertEquals(compare, true);
		if(repDTO3.getItem_orders().get(1).getCount() == 8)
			compare = true;
		assertEquals(compare, true);
		
	}

}
