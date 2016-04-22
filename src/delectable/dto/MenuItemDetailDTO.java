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

public class MenuItemDetailDTO {
	

		public int id;
		public String name;
		public float price_per_person;
		public int minimum_order;
		private List<CategoriesDTO> categories;

		public String create_date;
		public String last_modified_date;
		
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
		//@JsonProperty("categories")
		public void setCategories(List<CategoriesDTO> data) {
			categories = data;
		}
		
		public List<CategoriesDTO> getCategories() {
			return categories;
		}
		public String getCreate_date() {
			return create_date;
		}
		public void setCreate_date(String create_date) {
			this.create_date = create_date;
		}
		public String getLast_modified_date() {
			return last_modified_date;
		}
		public void setLast_modified_date(String last_modified_date) {
			this.last_modified_date = last_modified_date;
		}

}

