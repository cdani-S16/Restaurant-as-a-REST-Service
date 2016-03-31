package delectable.pojo;
import java.util.*;

import delectable.dto.MenuItemDTO;

public class MenuManager {
	

	private static List<MenuItem> menuItems = new ArrayList<MenuItem>();
	 
	public MenuManager(int noItems)
	{
		//menuItems = new ArrayList<MenuItem>();
	}
	
	public int AddItem(MenuItemDTO item)
	{
		MenuItem mi = new MenuItem(item);
		menuItems.add(mi);
		return mi.getId();
	}
	
    public List<MenuItem> getAllMenuItems() {
        return(menuItems);
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

	public List<MenuItem> getMenu()
	{
		return menuItems;
	}
}
