package delectable.logic;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import delectable.dto.*;
import delectable.pojo.MenuItem;
import delectable.pojo.Order;
import delectable.pojo.OrderDetailMenu;
import delectable.pojo.PersonalInfo;
import delectable.pojo.UniqueIdGenerator;

public class OrderManager {

	
	private static List<Order> Orders = null;
	private static OrderManager orderMan = null;

	public static List<Order> getOrders(){
		if(Orders == null){
			Orders = new ArrayList<Order>();
		}
		return Orders;
	}
	
	public static OrderManager getOrderMan(){
		if(orderMan == null){
			orderMan = new OrderManager();
		}
		return orderMan;
	}
	
    public List<OrderMiniDTO> getAllOrders() throws IllegalAccessException, InvocationTargetException {
    	List<OrderMiniDTO> orderEntriesDTO = new ArrayList<OrderMiniDTO>();
    	
    	for (int i = 0; i < getOrders().size(); i++) {
    		OrderMiniDTO temp = new OrderMiniDTO();
        	BeanUtils.copyProperties(temp, getOrders().get(i));
        	temp.setOrdered_by(getOrders().get(i).getPersonal_info().getEmail());
        	orderEntriesDTO.add(temp);
		}
        return(orderEntriesDTO);
    }

    public OrderDetailDTO getOrder(int id) throws IllegalAccessException, InvocationTargetException {
    	OrderDetailDTO orderDetailed = new OrderDetailDTO();
    	
    	BeanUtils.copyProperties(orderDetailed, getOrders().get(id));
    	orderDetailed.setOrder_detail(new ArrayList<OrderDetailMenuDTO>());
    	
    	//System.out.println(" the number of items in the orders " + Orders.get(id).getOrder_detail().size());
    	for(int i = 0; i< getOrders().get(id).getOrder_detail().size(); i++)
    	{	
    		OrderDetailMenuDTO temp = new OrderDetailMenuDTO();
    	
    		BeanUtils.copyProperties(temp, getOrders().get(id).getOrder_detail().get(i));
    		orderDetailed.getOrder_detail().add(temp);
    	}
    	
    	PersonalInfoDTO pi = new PersonalInfoDTO();
    	BeanUtils.copyProperties(pi, getOrders().get(id).getPersonal_info());
    	orderDetailed.setOrdered_by(pi);
    	//BeanUtils.copyProperties(orderDetailed.getOrdered_by(), Orders.get(id).getPersonal_info());
    	
    	//orderDetailed.settheOrderDetail(Orders.get(id).getOrder_detail());
        return(orderDetailed);
        
        /*
         *     	MenuItemDetailDTO miDto = new MenuItemDetailDTO();
    	BeanUtils.copyProperties(miDto,menuItems.get(id));
    	return miDto;
         */
    }
    
    public OrderAddedDTO addOrder(OrderDTO o) throws Exception {
    	//System.out.println("inside add order");
		Order oi = new Order();
		List<OrderDetailMenu> od = new ArrayList<OrderDetailMenu>();
		PersonalInfo pi = new PersonalInfo();
		BeanUtils.copyProperties(oi, o);
		BeanUtils.copyProperties(od, o.getOrder_detail());
		//copt items into the array
    	for(int i = 0; i< o.getOrder_detail().size(); i++)
    	{	
    		OrderDetailMenu temp = new OrderDetailMenu();
    	
    		BeanUtils.copyProperties(temp, o.getOrder_detail().get(i));
    		if(temp.getCount() < MenuManager.getMenu().getMenuItem(temp.getId()).getMinimum_order())
    			throw new NumberFormatException("Min order is not satisfied");
    		od.add(temp);
    	}
    	
		BeanUtils.copyProperties(pi, o.getPersonal_info());
		
		//copy contents from personal info to personal info
		
		oi.setOrderedBy(o.getPersonal_info().getEmail());
		oi.setpInfo(pi);
		oi.setoDetail(od);
		
		oi.setAmount(calcAmount(oi));
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date dateobj = new Date();
		//System.out.println(df.format(dateobj));
		oi.setOrder_date(df.format(dateobj));
		oi.setOrderedBy(o.getPersonal_info().getEmail());
		oi.setDelivery_date(o.getDelivery_date());
		//System.out.println(" incoming and just set dates " + o.getDelivery_date() 
			//	+ oi.getDelivery_date());
	   oi.setStatusManual("open");
		

	   //delivDate = calendar.getTime();
	   Date delivDate = new Date();
	   Calendar calendar = new GregorianCalendar();
	   calendar.setTime(df.parse(oi.getDelivery_date()));
	   //delivDate = calendar.getTime();
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || 
				calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			oi.setSurcharge(((MenuManager.getSurcharge().getSurcharge()/100)) * oi.getAmount());
		}
		
		
		oi.setId(UniqueIdGenerator.getUniqueOrderID());
		getOrders().add(oi);
		
		//add customer to list of customers
		CustomerManager.getCusMan().addCustomer(o.getPersonal_info());
		   
    	OrderAddedDTO oa = new OrderAddedDTO();
    	oa.setId(oi.getId());
    	//System.out.println("finished add order");
    	return(oa);
    }

    /*public Order getOrderDetail(int oid) {
        return(findById(oid));
    }

    private Order findById(int lid) {
        Iterator<Order> oli = Orders.listIterator();
        while(oli.hasNext()) {
        	Order o = oli.next();
            if(o.matchesId(lid)) return(o);
        }
        return(new NullOrderDTO());
    }*/
    
    public void CancelOrder(IdDTO order) throws Exception
    {
    	ChangeStatus(order, "cancelled");
    }
    
    public void DeliverOrder(IdDTO order) throws Exception
    {
    	ChangeStatus(order, "delivered");
    }
    
    private void ChangeStatus(IdDTO orderId,String status) throws Exception
    {
    	getOrders().get(orderId.getId()).setStatusManual(status);
    }
    
    public float calcAmount(Order calcOrdr) {
		float totAmt = 0;
    	for(int i = 0; i< calcOrdr.getOrder_detail().size() ; i++)
		{
    		int menuId = calcOrdr.getOrder_detail().get(i).getId();
			totAmt = totAmt + (MenuManager.getMenu().getPrice(menuId) * calcOrdr.getOrder_detail().get(i).getCount());
		}
    	
    	return totAmt;
	}
    
}
