package delectable.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MenuItemDTO implements Serializable{
	
	
		public MenuItemDTO()
		{
			categories = new ArrayList<CategoriesDTO>();
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public float getPrice_per_person() {
			return price_per_person;
		}
		public void setPrice_per_person(float price_per_person) {
			this.price_per_person = price_per_person;
		}
		public int getMinimum_order() {
			return minimum_order;
		}
		public void setMinimum_order(int minimum_order) {
			this.minimum_order = minimum_order;
		}

		private String name;
		private float price_per_person;
		private int minimum_order;
		private List<CategoriesDTO> categories;
		

	
		//@JsonProperty("categories")
		public void setCategories(List<CategoriesDTO> data) {
			categories = data;
		}
		
		public List<CategoriesDTO> getCategories() {
			return categories;
		}
		
		
		
	/*"categories": [{
  		"name": "organic"
  	}, {
  		"name": "vegetarian"
  	}]*/
	

}

