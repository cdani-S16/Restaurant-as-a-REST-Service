
package delectable.dto;

import java.util.ArrayList;
import java.util.List;

import delectable.pojo.Order;

public class ReportOrderDTO {

	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private List<OrderDetailDTO> orders = new ArrayList<OrderDetailDTO>();
	
	public List<OrderDetailDTO> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderDetailDTO> orders) {
		this.orders = orders;
	}
	
	
	

}
