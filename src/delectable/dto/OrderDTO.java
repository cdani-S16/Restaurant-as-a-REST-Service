package delectable.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO extends OrderMiniDTO {

	private String delivery_address;
	private String note;
	private PersonalInfoDTO personal_info;
	private List<OrderDetailMenuDTO> order_detail;

	
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
	public PersonalInfoDTO getPersonal_info() {
		return personal_info;
	}
	public void setPersonal_info(PersonalInfoDTO personal_info) {
		this.personal_info = personal_info;
	}
	public List<OrderDetailMenuDTO> getOrder_detail() {
		return order_detail;
	}
	public void setOrder_detail(List<OrderDetailMenuDTO> order_detail) {
		this.order_detail = order_detail;
	}

}
