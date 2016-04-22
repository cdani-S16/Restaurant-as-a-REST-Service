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
		int loopLength = ReportManager.getReportMan().getReportTypes().size();
		
		for(int i = 0; i < loopLength; i++)
		{
			int curId = ReportManager.getReportMan().getReportTypes().get(i).getId();
			if(curId == 800)
				assertEquals(ReportManager.getReportMan().getReportTypes().get(i).getName()
					,"Orders to deliver today");
			else if(curId == 801)
				assertEquals(ReportManager.getReportMan().getReportTypes().get(i).getName()
						,"Orders to deliver tomorrow");
			else if(curId == 802)
				assertEquals(ReportManager.getReportMan().getReportTypes().get(i).getName()
					,"Revenue report");
			else if(curId == 803)
				assertEquals(ReportManager.getReportMan().getReportTypes().get(i).getName()
					,"Orders delivery report");
		}
		
	}

	@Test
	public void testGetRevenueReport() throws Exception {
		//set surcharge
		AdminSchrDTO scr = new AdminSchrDTO();
		scr.setSurcharge(10);
		MenuManager.getMenu().setSurcharge(scr);
		
		//getting the menu and order ready for the report testing
		//adding sample menu item
		MenuItemDTO tempMenuItem = new MenuItemDTO();
		tempMenuItem.setMinimum_order(2);
		tempMenuItem.setPrice_per_person(2.5f);
		IdDTO testID = MenuManager.getMenu().AddItem(tempMenuItem);
		
		MenuItemDTO tempMenuItem2 = new MenuItemDTO();
		tempMenuItem2.setMinimum_order(4);
		tempMenuItem2.setPrice_per_person(3.5f);
		IdDTO testID2 = MenuManager.getMenu().AddItem(tempMenuItem2);

		//adding order 
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
		OrderManager.getOrderMan().addOrder(ordToADD);

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
		OrderManager.getOrderMan().addOrder(ordToADD2);
		
		RevenueReportDTO revRep = new RevenueReportDTO();
		String startDate = "20160420";
		String endDate = "20160425";
		revRep = ReportManager.getReportMan().getRevenueReport(startDate, endDate);
		boolean compare = false;
		if(revRep.getFood_revenue() == 33)
			compare = true;
		assertEquals(compare, true);
		if(revRep.getSurcharge_revenue() == 2.8)
			compare = true;
		assertEquals(compare, true);
		
		
		ReportOrderDTO repDTO1 = ReportManager.getReportMan().getOrderReport(800, "20160420");
		ReportOrderDTO repDTO2 = ReportManager.getReportMan().getOrderReport(801, "20160420");
		assertEquals(repDTO1.getOrders().size(),1);
		assertEquals(repDTO2.getOrders().size(),0);
		
		
		ReportAllOrdersDTO repDTO3;
		startDate = "20160419";
		endDate = "20160425";
		repDTO3 = ReportManager.getReportMan().getOrdersReport(startDate, endDate);
		compare = false;

		if(repDTO3.getItem_orders().get(0).getCount() == 2)
			compare = true;
		assertEquals(compare, true);
		if(repDTO3.getItem_orders().get(1).getCount() == 8)
			compare = true;
		assertEquals(compare, true);
		
	}

}
