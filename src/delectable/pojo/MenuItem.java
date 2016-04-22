package delectable.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuItem  implements Serializable{
	private int id;
	private String name;
	private float price_per_person;
	private int minimum_order;
	private List<Categories> categories;
	private String create_date;
	private String last_modified_date;


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

	public MenuItem()
	{
		this.id = UniqueIdGenerator.getUniqueMenuID();
		categories = new ArrayList<Categories>();
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

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}
	
}
