package delectable.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerDetailDTO extends CustomerDTO{

	private List<CustomerOrdersDTO> orders = new ArrayList<CustomerOrdersDTO>();

	public List<CustomerOrdersDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<CustomerOrdersDTO> orders) {
		this.orders = orders;
	}

	
}
