package delectable.dto;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MenuItemDTO {
	

		public int abc;
		public int id;
		public String name;
		public int price_per_person;
		public int minimum_order;
		public String[] categories;
	
	
	/*"categories": [{
  		"name": "organic"
  	}, {
  		"name": "vegetarian"
  	}]*/
	

}

