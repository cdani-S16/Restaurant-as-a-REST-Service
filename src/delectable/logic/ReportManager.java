package delectable.logic;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import delectable.dto.MenuItemDTO;
import delectable.dto.OrderDetailDTO;
import delectable.dto.ReportAllOrdersDTO;
import delectable.dto.PersonalInfoDTO;
import delectable.dto.ReportMenuItemOrdersDTO;
import delectable.dto.ReportOrderDTO;
import delectable.dto.RevenueReportDTO;
import delectable.pojo.*;

public class ReportManager {

	public static ReportManager reportMan = new ReportManager();
	private static List<ReportTypes> reportTypes = new ArrayList<ReportTypes>();
	private static ReportOrder reportOrder = new ReportOrder();
	
	
	public List<ReportTypes> getReportTypes() throws IllegalAccessException, InvocationTargetException
	{
		ReportTypes r = new ReportTypes();
		r.setId(800);
		r.setName("Orders to deliver today");
		reportTypes.add(r);
		r = new ReportTypes();
		r.setId(801);
		r.setName("Orders to deliver tomorrow");
		reportTypes.add(r);
		r = new ReportTypes();
		r.setId(802);
		r.setName("Revenue report");
		reportTypes.add(r);
		r = new ReportTypes();
		r.setId(803);
		r.setName("Orders delivery report");
		reportTypes.add(r);
		
		List<ReportTypes> rTypes = new ArrayList<ReportTypes>();
		
		for(int i = 0; i < reportTypes.size(); i++)
		{
			ReportTypes temp = new ReportTypes();
			BeanUtils.copyProperties(temp, reportTypes.get(i));
			rTypes.add(temp);
		}
		
		return rTypes;
	}
	
	public ReportOrderDTO getOrderReport(int rid, String dateStr) throws IllegalAccessException, InvocationTargetException, ParseException
	{
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date currentDate = new Date();
		Date nextDate = new Date();
		Calendar calendar = new GregorianCalendar(/* remember about timezone! */);
		calendar.setTime(df.parse(dateStr));
		currentDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		nextDate = calendar.getTime();
		ReportOrderDTO rODTO = new ReportOrderDTO();
		Date date;
		rODTO.setId(rid);
		if(rid == 800)
		{
			rODTO.setName("Orders to deliver today");
			date = currentDate;
		}
		else
		{	
			rODTO.setName("Order to deliver tomorrow");
			date = nextDate;
		}
		for(int i = 0; i< OrderManager.Orders.size(); i++)
		{
			String delivDate = OrderManager.order.getOrder(i).getDelivery_date();
			calendar.setTime(df.parse(delivDate));
			Date Deliv_Date = calendar.getTime();
			if(Deliv_Date.equals(date))
			{	
				OrderDetailDTO temp = new OrderDetailDTO();
				BeanUtils.copyProperties(temp,OrderManager.order.getOrder(i));
				rODTO.getOrders().add(temp);
			}
		}

		return rODTO;
	}

	/*private void populateReport(String date) throws IllegalAccessException, InvocationTargetException {
		reportOrder = new ReportOrder();
		for(int i = 0; i< OrderManager.Orders.size(); i++)
		{
			Order temp = new Order();
			//    	BeanUtils.copyProperties(orderDetailed, Orders.get(id));
			BeanUtils.copyProperties(temp, OrderManager.Orders.get(i));
			reportOrder.getOrders().add(temp);
			//ReportOrder tempRO = new ReportOrder();
			
			//reportOrders;
		}
	}*/
	
	public RevenueReportDTO getRevenueReport(String startDate, String endDate) throws ParseException, IllegalAccessException, InvocationTargetException
	{
		RevenueReportDTO revRep = new RevenueReportDTO();
		/*
		 * 	
		private int id;
		private String name;
		private String start_date;
		private String end_date;
		private int orders_placed;
		private int orders_cancelled;
		private int orders_open;
		private int food_revenue;
		private int surcharge_revenue;
		 */
		revRep.setEnd_date(endDate);
		revRep.setStart_date(startDate);
		
	   DateFormat df = new SimpleDateFormat("yyyyMMdd");
	   Date End_date = new Date();
	   Calendar calendar = new GregorianCalendar();
	   calendar.setTime( df.parse(endDate));
	   calendar.add(Calendar.DATE, 1);
	   End_date = calendar.getTime();
	   
	   Date Start_date = new Date();
	   calendar.setTime( df.parse(startDate));
	   calendar.add(Calendar.DATE, -1);
	   Start_date = calendar.getTime();
		
		/*System.out.println("the statt and end data " 
				+ df.format(Start_date) 
				+ df.format(End_date));*/

		revRep.setFood_revenue(0);
		revRep.setOrders_cancelled(0);
		revRep.setOrders_placed(0);
		revRep.setOrders_open(0);
		revRep.setSurcharge_revenue(0);
		
		for(int i = 0; i< OrderManager.Orders.size(); i++)
		{
			String delivDate = OrderManager.order.getOrder(i).getDelivery_date();
			calendar.setTime(df.parse(delivDate));
			Date Deliv_Date = calendar.getTime();
			
			if(Start_date.before(Deliv_Date)
					&& End_date.after(Deliv_Date))
			{	
				revRep.setOrders_placed(revRep.getOrders_placed() + 1);
				if(OrderManager.order.getOrder(i).getStatus().equals("cancelled"))
				{
					revRep.setOrders_cancelled(revRep.getOrders_cancelled() + 1);
				}
				if(OrderManager.order.getOrder(i).getStatus().equals("open"))
				{
					revRep.setOrders_open(revRep.getOrders_open() + 1);
					revRep.setFood_revenue(revRep.getFood_revenue() + OrderManager.order.getOrder(i).getAmount());
					revRep.setSurcharge_revenue(revRep.getSurcharge_revenue() + OrderManager.order.getOrder(i).getSurcharge());				
				}				
			}
		}
		
		return revRep;
	}
	
	public ReportAllOrdersDTO getOrdersReport(String startDate, String endDate) throws ParseException, IllegalAccessException, InvocationTargetException
	{
		ReportAllOrdersDTO ordsRep = new ReportAllOrdersDTO();
		 /*{
			  	"id": 804,
			  	"name": "Order report",
			  	"start_date": "20160101",
			  	"end_date": "20160331",
			  	"orders_placed": 47,
			  	"orders_cancelled": 2,
			  	"orders_open": 45,
				"item_orders" : [
				{
					"id"
					"name":
					"count":
				},
				{	
					"id"
					"name":
					"count"
				},
				]
			  }*/
		ordsRep.setEnd_date(endDate);
		ordsRep.setStart_date(startDate);
		List<ReportMenuItemOrdersDTO> menuList = new ArrayList<ReportMenuItemOrdersDTO>();
		
	   DateFormat df = new SimpleDateFormat("yyyyMMdd");
	   Date End_date = new Date();
	   Calendar calendar = new GregorianCalendar();
	   calendar.setTime( df.parse(endDate));
	   calendar.add(Calendar.DATE, 1);
	   End_date = calendar.getTime();
	   
	   Date Start_date = new Date();
	   calendar.setTime( df.parse(startDate));
	   calendar.add(Calendar.DATE, -1);
	   Start_date = calendar.getTime();
		
		/*System.out.println("the statt and end data " 
				+ df.format(Start_date) 
				+ df.format(End_date));*/

	   ordsRep.setOrders_cancelled(0);
	   ordsRep.setOrders_placed(0);
	   ordsRep.setOrders_open(0);
		for(int j=0; j< MenuManager.menu.getAllMenuItems().size(); j++)
		{
			ReportMenuItemOrdersDTO temp = new ReportMenuItemOrdersDTO();
			BeanUtils.copyProperties(temp, MenuManager.menu.getMenuItem(j));
			//temp.setCount(0);
			int tempCount = 0;
			for(int i = 0; i< OrderManager.Orders.size(); i++)
			{
				String delivDate = OrderManager.order.getOrder(i).getDelivery_date();
				calendar.setTime(df.parse(delivDate));
				Date Deliv_Date = calendar.getTime();
				
				if(Start_date.before(Deliv_Date)
						&& End_date.after(Deliv_Date))
				{	
					//revRep.setOrders_placed(revRep.getOrders_placed() + 1);
					if(OrderManager.order.getOrder(i).getStatus()
							.equals("cancelled"))
					{
						//revRep.setOrders_cancelled(revRep.getOrders_cancelled() + 1);
					}
					if(OrderManager.order.getOrder(i).getStatus().equals("open"))
					{
						for(int k = 0; k < OrderManager.order.getOrder(i)
								.getOrder_detail().size(); k++)
							tempCount = tempCount + OrderManager.order.getOrder(i).getOrder_detail().get(k).getItemCount(j); 
					}				
				}
			}
			temp.setCount(tempCount);
			menuList.add(temp);
		}
		ordsRep.setItem_orders(menuList);
		return ordsRep;
	}
}
