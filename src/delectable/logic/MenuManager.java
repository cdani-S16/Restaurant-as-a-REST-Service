package delectable.logic;
import delectable.pojo.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import org.apache.commons.beanutils.BeanUtils;

import delectable.dto.MenuItemDTO;
import delectable.pojo.MenuItem;

public class MenuManager {
	

	private static List<MenuItem> menuItems = new ArrayList<MenuItem>();
	
	
	public MenuManager()
	{
		//menuItems = new ArrayList<MenuItem>();
	}
	
	public int AddItem(MenuItemDTO item) throws IllegalAccessException, InvocationTargetException
	{
		MenuItem mi = new MenuItem();
		menuItems.add(mi);
		BeanUtils.copyProperties(mi, item);
		return mi.getId();
	}
	
    public List<MenuItemDTO> getAllMenuItems() throws IllegalAccessException, InvocationTargetException {
    	
    	//Mapper mapper = new DozerBeanMapper();
    	List<MenuItemDTO> menuItemsDTO = new ArrayList<MenuItemDTO>();
    	
    	
    	for (int i = 0; i < menuItems.size(); i++) {

    		MenuItemDTO temp = new MenuItemDTO();
        	BeanUtils.copyProperties(temp, menuItems.get(i));
        	menuItemsDTO.add(temp);
		}

        return(menuItemsDTO);
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
