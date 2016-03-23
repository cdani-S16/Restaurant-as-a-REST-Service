package delectable.pojo;

public class OldMenuItem {
	
	private String name;
	private float price;
	private int minQty;
	private int type; 
	//{organic,gluten free,vegetarian,vegan,dairy free};
	
	public OldMenuItem(String name, float price,int minQty, int type )
	{
		 if (!validateType(type)) {
		      throw new IllegalArgumentException("Food category is invalid.");
		    }
		 this.name = name;
		 this.price = price;
		 this.minQty = minQty;
		 this.type = type;
	}
	
	private boolean validateType(int type)
	{
		if(type >=0 && type<= 4)
			return true;
		else
			return false;
	}

}
