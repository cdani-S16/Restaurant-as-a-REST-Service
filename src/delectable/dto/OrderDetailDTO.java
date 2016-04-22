package delectable.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailDTO {
	private int id;

	private float amount;
	private float surcharge;
	private String status;
	private String order_date;
	private String delivery_date;
	private PersonalInfoDTO ordered_by;
	private String delivery_address;
	private String note;
	private List<OrderDetailMenuDTO> order_detail;
	
	public void setOrder_detail(List<OrderDetailMenuDTO> order_detail) {
		this.order_detail = order_detail;
	}

	public List<OrderDetailMenuDTO> getOrder_detail() {
		return order_detail;
	}

	public OrderDetailDTO()
	{
		order_detail = new ArrayList<OrderDetailMenuDTO>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getSurcharge() {
		return surcharge;
	}
	public void setSurcharge(float surcharge) {
		this.surcharge = surcharge;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public PersonalInfoDTO getOrdered_by() {
		return ordered_by;
	}
	public void setOrdered_by(PersonalInfoDTO ordered_by) {
		this.ordered_by = ordered_by;
	}
	public String getDelivery_address() {
		return delivery_address;
	}
	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	/*public void settheOrderDetail(List<OrderDetailMenuDTO> a){
		for(int i = 0; i< a.size();i++)
		{
			order_detail.add(a.get(i));
		}
	}*/
	
	

}
