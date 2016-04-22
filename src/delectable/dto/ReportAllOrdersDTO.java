package delectable.dto;

import java.util.List;

public class ReportAllOrdersDTO {
/*
 * 
 * {
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
  }
 */
	
	private int id;
	private String name;
	private String start_date;
	private String end_date;
	private int orders_placed;
	private int orders_cancelled;
	private int orders_open;
	private List<ReportMenuItemOrdersDTO> item_orders;
	
	public List<ReportMenuItemOrdersDTO> getItem_orders() {
		return item_orders;
	}
	public void setItem_orders(List<ReportMenuItemOrdersDTO> item_orders) {
		this.item_orders = item_orders;
	}
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
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getOrders_placed() {
		return orders_placed;
	}
	public void setOrders_placed(int orders_placed) {
		this.orders_placed = orders_placed;
	}
	public int getOrders_cancelled() {
		return orders_cancelled;
	}
	public void setOrders_cancelled(int orders_cancelled) {
		this.orders_cancelled = orders_cancelled;
	}
	public int getOrders_open() {
		return orders_open;
	}
	public void setOrders_open(int orders_open) {
		this.orders_open = orders_open;
	}

	
}
