package delectable.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	
	private int id;
	private String order_date;
	private String delivery_date;
	private float amount;
	private float surcharge;
	private String delivery_address;
	private String note;
	private String cancel_url;
	private String status;
	private String orderedBy;

	private PersonalInfo personal_info;
	private List<OrderDetailMenu> order_detail;
	//private PersonalInfo personal_info;
	
	
	public int getId() {
		return id;
	}

	public String getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
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

	public String getCancel_url() {
		return cancel_url;
	}

	public void setCancel_url(String cancel_url) {
		this.cancel_url = cancel_url;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float f) {
		this.amount = f;
	}

	public float getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(float f) {
		this.surcharge = f;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatusManual(String status) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date dateobj = new Date();
		//System.out.println("Date " + df.format(dateobj)+ 
		//		" exisint date in order" + getDelivery_date());
		if(this.getDelivery_date().equals(df.format(dateobj)) 
				&& status.equals("cancelled"))
			throw new Exception("Cannot cancel, it's due today");
		this.status = status;
	}

	public PersonalInfo getPersonal_info() {
		return personal_info;
	}

	public List<OrderDetailMenu> getOrder_detail() {
		return order_detail;
	}

	public void setpInfo(PersonalInfo personal_info) {
		this.personal_info = personal_info;
	}
	/*
	public List<OrderDetail> getOrder_detail() {
		/*List<OrderDetail> copy_of_order_detail = new ArrayList<OrderDetail>(order_detail.size());
		for (OrderDetail foo: order_detail) {
			boolean add = copy_of_order_detail.add((OrderDetail)foo.clone());
		}
	return copy_of_order_detail;
		return order_detail;
	}
	 */
	public void setoDetail(List<OrderDetailMenu> order_detail) {
		this.order_detail = order_detail;
	}

	public boolean matchesId(int lid) {
        return(lid == this.id);
    }
    /*public Order()
    {
    	personal_info = new PersonalInfo();
    	order_detail = new ArrayList<OrderDetailMenu>();
    }*/

}
