package delectable.pojo;
import java.util.*;

public class Menu {
	
	public int maxId;
	ArrayList<MenuItem> menuItems;
	
	public Menu(int noItems)
	{
		maxId = 100;
		menuItems = new ArrayList<MenuItem>();
	}
	
	public int AddItem(MenuItem item)
	{
		int menuItemId;
		menuItemId = maxId;
		item.setId(menuItemId);
		menuItems.add(item);
		maxId++;
		return menuItemId;
	}

	public boolean AlterItem(MenuItem item)
	{
		boolean alterFlag = false;
		int oldIndex = FindItemById(item.getId());
		if(oldIndex != -1)
			alterFlag = true;
		menuItems.remove(oldIndex);
		menuItems.add(item);
		return alterFlag;
	}
	
	public int FindItemById(int findId)
	{
		int itemIndex = -1;
		for(int i = 0; i< menuItems.size() ; i++)
		{
			if(menuItems.get(i).getId() == findId)
				{
					itemIndex = i;
					break;
				}
		}
		return itemIndex;
	}

	public ArrayList<MenuItem> getMenu()
	{
		return menuItems;
	}
}
