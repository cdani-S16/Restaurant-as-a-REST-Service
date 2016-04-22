package delectable.pojo;

import java.util.ArrayList;
import java.util.List;

public class ReportOrder {

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
	private List<Order> orders = new ArrayList<Order>();

	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
