package delectable.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO extends OrderMiniDTO {
    /* PUT
    * {
  	"delivery_date": "20160301",
  	"delivery_address": "10 West 31st ST, Chicago IL 60616",
  	"personal_info": {
  		"name": "Virgil B",
  		"email": "virgil@example.com",
  		"phone": "312-456-7890"
  	},
  	"note": "Room SB-214",
  	"order_detail": [{
  		"id": 123,
  		"count": 8
  	}, {
  		"id": 124,
  		"count": 24
  	}]
  }
  
   	"amount": 61.92,
  	"surcharge": 0,
  	"status": "open",
  	"order_date": "20160229",
  	"delivery_date": "20160301",*/
	
	/*order mini
	 * 
	 * 	private int id;
	private String order_date;
	private String delivery_date;
	private int amount;
	private int surcharge;
	private String status;
	private String ordered_by;
	
	 */

	private String delivery_address;
	private String note;
	
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
	private PersonalInfoDTO personal_info;
	private List<OrderDetailMenuDTO> order_detail;
	
	/*public OrderDTO()
	{
		personal_info = new PersonalInfoDTO();
		order_detail = new ArrayList<OrderDetailMenuDTO>();
	}*/
}
