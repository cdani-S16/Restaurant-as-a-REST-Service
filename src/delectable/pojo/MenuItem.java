package delectable.pojo;

public class MenuItem {
/*"id": 123,
  	"name": "Lasagna",
  	"price_per_person": 2.49,
  	"minimum_order": 6,
  	"categories": [{
  		"name": "organic"
  	}, {
  		"name": "vegetarian"
  	}]*/
	
	private int id;
	private String name;
	private int price_per_person;
	private int minimum_order;
	private String[] categories;

	
	public MenuItem()
	{
		this.id = UniqueIdGenerator.getUniqueMenuID();
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

	public int getPrice_per_person() {
		return price_per_person;
	}

	public void setPrice_per_person(int price_per_person) {
		this.price_per_person = price_per_person;
	}

	public int getMinimum_order() {
		return minimum_order;
	}

	public void setMinimum_order(int minimum_order) {
		this.minimum_order = minimum_order;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	
}
