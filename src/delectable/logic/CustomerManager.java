package delectable.logic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import delectable.dto.CustomerDTO;
import delectable.dto.CustomerDetailDTO;
import delectable.dto.CustomerOrdersDTO;
import delectable.dto.PersonalInfoDTO;
import delectable.pojo.Customer;

public class CustomerManager {

	/*
	 * [{
	  	"id": 701,
	  	"name": "Virgil B",
	  	"email": "virgil@example.com",
	  	"phone": "312-456-7890"
	  }, {
	  	"id": 713,
	  	"name": "Bob Sample",
	  	"email": "bob@example.com",
	  	"phone": "312-456-7098"
	  }]
	 */
	public static List<Customer> customers = new ArrayList<Customer>();
	public static CustomerManager cusMan = new CustomerManager();
	
	public void addCustomer(PersonalInfoDTO cDto) throws IllegalAccessException, InvocationTargetException
	{
		//System.out.println("add cus called");
		boolean isPresent = false;
		boolean alwaysFalse = false;
		int i = 0;
		if(customers.size() == 0){
			
			Customer c = new Customer();
			//System.out.println("  Customer size 0 is called ispresent val" 
			//			+ isPresent);
			BeanUtils.copyProperties(c, cDto);
			customers.add(c);
			return;
		}
		else
		{
			for(i = 0; i<customers.size() ; i = i+1)
			{
				//System.out.println(" Comparing this and ths "
				//		+ customers.get(i).getEmail() 
				//		+ cDto.getEmail());
				if(customers.get(i).getEmail().equals(cDto.getEmail()))
				{
					customers.get(i).updatePhoneName(cDto.getPhone(), cDto.getName());
					isPresent = true;
					//System.out.println("  value of i and ispresent in if" 
					//		+ " " + i + " " + isPresent);
					//return;
				}
				//System.out.println("the value of i " + i);

			}
			if((i == customers.size()) && (isPresent == alwaysFalse))
			{
				Customer c = new Customer();
				//System.out.println("  value of ispresent and i in else" 
				//			+ isPresent + " " + i);
				BeanUtils.copyProperties(c, cDto);
				customers.add(c);
				//return;
			}
		}
		isPresent = false;
		i = 0;
		//System.out.println("Size of customers is " + customers.size());
	}
	
	public List<CustomerDTO> getAllCustomers() throws IllegalAccessException, InvocationTargetException
	{
		List<CustomerDTO> cusDTO = new ArrayList<CustomerDTO>();
		for(int i = 0; i < customers.size(); i++)
		{
			CustomerDTO temp = new CustomerDTO();
			BeanUtils.copyProperties(temp, customers.get(i));
			temp.setId(customers.get(i).getId());
			cusDTO.add(temp);
		}
		
		return cusDTO;
	}
	
	public List<CustomerDTO> getAllCustomersMatching(String sString) throws IllegalAccessException, InvocationTargetException
	{
		List<CustomerDTO> cusDTO = new ArrayList<CustomerDTO>();
		for(int i = 0; i < customers.size(); i++)
		{
			if(customers.get(i).getName().contains(sString)
					|| customers.get(i).getEmail().contains(sString) 
					|| customers.get(i).getPhone().contains(sString))
			{
			CustomerDTO temp = new CustomerDTO();
			BeanUtils.copyProperties(temp, customers.get(i));
			temp.setId(customers.get(i).getId());
			cusDTO.add(temp);
			}
		}
		
		return cusDTO;
	}
	
	public CustomerDetailDTO getCustomer(int id) throws IllegalAccessException, InvocationTargetException
	{
		//id = id - 1;
		
		CustomerDetailDTO cusDet = new CustomerDetailDTO();
		
		BeanUtils.copyProperties(cusDet, customers.get(id));
		
		//updating order details
		String email = cusDet.getEmail();
		
		List<CustomerOrdersDTO> cusOrd = new ArrayList<CustomerOrdersDTO>();
		for(int i = 0; i< OrderManager.Orders.size(); i++)
		{
			if(OrderManager.Orders.get(i).getOrderedBy().equals(email))
			{
				//add it  to the orders part of the customer
				
				CustomerOrdersDTO temp = new CustomerOrdersDTO();
				BeanUtils.copyProperties(temp, OrderManager.Orders.get(i));
				cusOrd.add(temp);
			}
		}
		cusDet.setOrders(cusOrd);
		return cusDet;
	}
	

}
