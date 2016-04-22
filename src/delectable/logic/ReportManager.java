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

	private static ReportManager reportMan = null;
	private static List<ReportTypes> reportTypes = null;
	
	public static ReportManager getReportMan()
	{
		if(reportMan == null)
			reportMan = new ReportManager();
		return reportMan;
	}
	
	public static List<ReportTypes> getReportTypesDS()
	{
		if(reportTypes == null)
			reportTypes = new ArrayList<ReportTypes>();
		return reportTypes;
	}
	
	public List<ReportTypes> getReportTypes() throws IllegalAccessException, InvocationTargetException
	{
		ReportTypes r = new ReportTypes();
		r.setId(800);
		r.setName("Orders to deliver today");
		getReportTypesDS().add(r);
		r = new ReportTypes();
		r.setId(801);
		r.setName("Orders to deliver tomorrow");
		getReportTypesDS().add(r);
		r = new ReportTypes();
		r.setId(802);
		r.setName("Revenue report");
		getReportTypesDS().add(r);
		r = new ReportTypes();
		r.setId(803);
		r.setName("Orders delivery report");
		getReportTypesDS().add(r);
		
		List<ReportTypes> rTypes = new ArrayList<ReportTypes>();
		
		for(int i = 0; i < getReportTypesDS().size(); i++)
		{
			ReportTypes temp = new ReportTypes();
			BeanUtils.copyProperties(temp, getReportTypesDS().get(i));
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
		for(int i = 0; i< OrderManager.getOrders().size(); i++)
		{
			String delivDate = OrderManager.getOrderMan().getOrder(i).getDelivery_date();
			calendar.setTime(df.parse(delivDate));
			Date Deliv_Date = calendar.getTime();
			if(Deliv_Date.equals(date))
			{	
				OrderDetailDTO temp = new OrderDetailDTO();
				BeanUtils.copyProperties(temp,OrderManager.getOrderMan().getOrder(i));
				rODTO.getOrders().add(temp);
			}
		}

		return rODTO;
	}

	
	public RevenueReportDTO getRevenueReport(String startDate, String endDate) throws ParseException, IllegalAccessException, InvocationTargetException
	{
		RevenueReportDTO revRep = new RevenueReportDTO();

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

		revRep.setFood_revenue(0);
		revRep.setOrders_cancelled(0);
		revRep.setOrders_placed(0);
		revRep.setOrders_open(0);
		revRep.setSurcharge_revenue(0);
		
		for(int i = 0; i< OrderManager.getOrders().size(); i++)
		{
			String delivDate = OrderManager.getOrderMan().getOrder(i).getDelivery_date();
			calendar.setTime(df.parse(delivDate));
			Date Deliv_Date = calendar.getTime();
			
			if(Start_date.before(Deliv_Date)
					&& End_date.after(Deliv_Date))
			{	
				revRep.setOrders_placed(revRep.getOrders_placed() + 1);
				if(OrderManager.getOrderMan().getOrder(i).getStatus().equals("cancelled"))
				{
					revRep.setOrders_cancelled(revRep.getOrders_cancelled() + 1);
				}
				if(OrderManager.getOrderMan().getOrder(i).getStatus().equals("open"))
				{
					revRep.setOrders_open(revRep.getOrders_open() + 1);
					revRep.setFood_revenue(revRep.getFood_revenue() + OrderManager.getOrderMan().getOrder(i).getAmount());
					revRep.setSurcharge_revenue(revRep.getSurcharge_revenue() + OrderManager.getOrderMan().getOrder(i).getSurcharge());				
				}				
			}
		}
		
		return revRep;
	}
	
	public ReportAllOrdersDTO getOrdersReport(String startDate, String endDate) throws ParseException, IllegalAccessException, InvocationTargetException
	{
		ReportAllOrdersDTO ordsRep = new ReportAllOrdersDTO();
		
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
		
		ordsRep.setOrders_cancelled(0);
		ordsRep.setOrders_placed(0);
		ordsRep.setOrders_open(0);

		for(int j=0; j< MenuManager.getMenu().getAllMenuItems().size(); j++)
		{
			ReportMenuItemOrdersDTO temp = new ReportMenuItemOrdersDTO();
			BeanUtils.copyProperties(temp, MenuManager.getMenu().getMenuItem(j));
			
			int tempCount = 0;
			for(int i = 0; i< OrderManager.getOrders().size(); i++)
			{
				String delivDate = OrderManager.getOrderMan().getOrder(i).getDelivery_date();
				calendar.setTime(df.parse(delivDate));
				Date Deliv_Date = calendar.getTime();
				
				if(Start_date.before(Deliv_Date)
						&& End_date.after(Deliv_Date))
				{	
					if(OrderManager.getOrderMan().getOrder(i).getStatus().equals("open"))
					{
						for(int k = 0; k < OrderManager.getOrderMan().getOrder(i)
								.getOrder_detail().size(); k++)
							tempCount = tempCount + OrderManager.getOrderMan().getOrder(i).getOrder_detail().get(k).getItemCount(j); 
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
