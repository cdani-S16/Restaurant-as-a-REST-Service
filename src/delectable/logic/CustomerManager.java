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
	private static List<Customer> customers = null;
	private static CustomerManager cusMan = null;
	
	private CustomerManager(){
		
	}
	public static CustomerManager getCusMan(){
		if(cusMan == null){
			cusMan = new CustomerManager();
		}
		return cusMan;
	}
	
	public static List<Customer> getCustomers(){
		if(customers == null){
			customers = new ArrayList<Customer>();
		}
		return customers;
	}

	public void addCustomer(PersonalInfoDTO cDto) throws IllegalAccessException, InvocationTargetException
	{
		//System.out.println("add cus called");
		boolean isPresent = false;
		boolean alwaysFalse = false;
		int i = 0;
		if(getCustomers().size() == 0){
			
			Customer c = new Customer();
			//System.out.println("  Customer size 0 is called ispresent val" 
			//			+ isPresent);
			BeanUtils.copyProperties(c, cDto);
			getCustomers().add(c);
			return;
		}
		else
		{
			for(i = 0; i<getCustomers().size() ; i = i+1)
			{
				//System.out.println(" Comparing this and ths "
				//		+ customers.get(i).getEmail() 
				//		+ cDto.getEmail());
				if(getCustomers().get(i).getEmail().equals(cDto.getEmail()))
				{
					getCustomers().get(i).updatePhoneName(cDto.getPhone(), cDto.getName());
					isPresent = true;
					//System.out.println("  value of i and ispresent in if" 
					//		+ " " + i + " " + isPresent);
					//return;
				}
				//System.out.println("the value of i " + i);

			}
			if((i == getCustomers().size()) && (isPresent == alwaysFalse))
			{
				Customer c = new Customer();
				//System.out.println("  value of ispresent and i in else" 
				//			+ isPresent + " " + i);
				BeanUtils.copyProperties(c, cDto);
				getCustomers().add(c);
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
		for(int i = 0; i < getCustomers().size(); i++)
		{
			CustomerDTO temp = new CustomerDTO();
			BeanUtils.copyProperties(temp, getCustomers().get(i));
			temp.setId(getCustomers().get(i).getId());
			cusDTO.add(temp);
		}
		
		return cusDTO;
	}
	
	public List<CustomerDTO> getAllCustomersMatching(String sString) throws IllegalAccessException, InvocationTargetException
	{
		List<CustomerDTO> cusDTO = new ArrayList<CustomerDTO>();
		for(int i = 0; i < getCustomers().size(); i++)
		{
			if(getCustomers().get(i).getName().contains(sString)
					|| getCustomers().get(i).getEmail().contains(sString) 
					|| getCustomers().get(i).getPhone().contains(sString))
			{
			CustomerDTO temp = new CustomerDTO();
			BeanUtils.copyProperties(temp, getCustomers().get(i));
			temp.setId(getCustomers().get(i).getId());
			cusDTO.add(temp);
			}
		}
		
		return cusDTO;
	}
	
	public CustomerDetailDTO getCustomer(int id) throws IllegalAccessException, InvocationTargetException
	{
		//id = id - 1;
		
		CustomerDetailDTO cusDet = new CustomerDetailDTO();
		
		BeanUtils.copyProperties(cusDet, getCustomers().get(id));
		
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
