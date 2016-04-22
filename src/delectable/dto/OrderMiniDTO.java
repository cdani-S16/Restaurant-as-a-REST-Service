package delectable.dto;

public class OrderMiniDTO {
/*
 *   	"id": 456,
  	"order_date": "20160229",
  	"delivery_date": "20160301",
  	"amount": 61.92,
  	"surcharge": 0,
  	"status": "open",
  	"ordered_by": "virgil@example.com"
 */
	private int id;
	private String order_date;
	private String delivery_date;
	private float amount;
	private int surcharge;
	private String status;
	private String ordered_by;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getSurcharge() {
		return surcharge;
	}
	public void setSurcharge(int surcharge) {
		this.surcharge = surcharge;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrdered_by() {
		return ordered_by;
	}
	public void setOrdered_by(String ordered_by) {
		this.ordered_by = ordered_by;
	}

}
