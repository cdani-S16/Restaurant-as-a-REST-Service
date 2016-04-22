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

	public void addCustomer(PersonalInfoDTO cDto) 
			throws IllegalAccessException, InvocationTargetException
	{
		boolean isPresent = false;
		boolean alwaysFalse = false;
		int i = 0;
		if(getCustomers().size() == 0){
			Customer c = new Customer();
			BeanUtils.copyProperties(c, cDto);
			getCustomers().add(c);
			return;
		}
		else
		{
			for(i = 0; i<getCustomers().size() ; i = i+1)
			{
				if(getCustomers().get(i).getEmail().equals(cDto.getEmail()))
				{
					getCustomers().get(i).updatePhoneName(cDto.getPhone(), cDto.getName());
					isPresent = true;
				}
			}
			if((i == getCustomers().size()) && (isPresent == alwaysFalse))
			{
				Customer c = new Customer();
				BeanUtils.copyProperties(c, cDto);
				getCustomers().add(c);
			}
		}
		isPresent = false;
		i = 0;
	}
	
	public List<CustomerDTO> getAllCustomers() 
			throws IllegalAccessException, InvocationTargetException
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
	
	public List<CustomerDTO> getAllCustomersMatching(String sString) 
			throws IllegalAccessException, InvocationTargetException
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
		CustomerDetailDTO cusDet = new CustomerDetailDTO();
		BeanUtils.copyProperties(cusDet, getCustomers().get(id));
		String email = cusDet.getEmail();
		List<CustomerOrdersDTO> cusOrd = new ArrayList<CustomerOrdersDTO>();
		for(int i = 0; i< OrderManager.getOrders().size(); i++)
		{
			if(OrderManager.getOrders().get(i).getOrderedBy().equals(email))
			{
				CustomerOrdersDTO temp = new CustomerOrdersDTO();
				BeanUtils.copyProperties(temp, OrderManager.getOrders().get(i));
				cusOrd.add(temp);
			}
		}
		cusDet.setOrders(cusOrd);
		return cusDet;
	}
	

}
