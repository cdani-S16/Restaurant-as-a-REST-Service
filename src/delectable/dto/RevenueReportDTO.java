package delectable.dto;

public class RevenueReportDTO {

	/*
	 *  {
  	"id": 803,
  	"name": "Revenue report",
  	"start_date": "20160101",
  	"end_date": "20160331",
  	"orders_placed": 47,
  	"orders_cancelled": 2,
  	"orders_open": 45,
  	"food_revenue": 513.98,
  	"surcharge_revenue": 15
  }
	 */
	
	private int id;
	private String name;
	private String start_date;
	private String end_date;
	private int orders_placed;
	private int orders_cancelled;
	private int orders_open;
	private float food_revenue;
	private float surcharge_revenue;
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
	public float getFood_revenue() {
		return food_revenue;
	}
	public void setFood_revenue(float food_revenue) {
		this.food_revenue = food_revenue;
	}
	public float getSurcharge_revenue() {
		return surcharge_revenue;
	}
	public void setSurcharge_revenue(float f) {
		this.surcharge_revenue = f;
	}
	
	
	
}
