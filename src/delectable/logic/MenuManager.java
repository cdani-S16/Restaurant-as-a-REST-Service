package delectable.logic;
import delectable.pojo.*;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.beanutils.BeanUtils;

import delectable.dto.AdminSchrDTO;
import delectable.dto.IdDTO;
import delectable.dto.MenuItemDTO;
import delectable.dto.MenuItemDetailDTO;
import delectable.dto.MenuItemIdDTO;
import delectable.dto.MenuItemIdPriceDTO;
import delectable.pojo.MenuItem;

public class MenuManager {
	

	private static List<MenuItem> menuItems;
	private static MenuManager menu = null;
	private static float surcharge = 0;

	public static MenuManager getMenu(){
		if(menu == null){
			menu = new MenuManager();
		}
		return menu;
	}
	
	public static List<MenuItem> getMenuItems()
	{
		if(menuItems == null)
		{
			menuItems = new ArrayList<MenuItem>();
		}
		return menuItems;
	}
	
	public MenuManager()
	{
	}
	
	public IdDTO AddItem(MenuItemDTO item) throws IllegalAccessException, InvocationTargetException
	{
		IdDTO idObj = new IdDTO();
		MenuItem mi = new MenuItem();
		BeanUtils.copyProperties(mi, item);
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date dateobj = new Date();
		mi.setCreate_date(df.format(dateobj));
		mi.setLast_modified_date(df.format(dateobj));
		getMenuItems().add(mi);
		idObj.setId(mi.getId());
		return idObj;
		
	}
	
    public List<MenuItemIdDTO> getAllMenuItems() 
    		throws IllegalAccessException, InvocationTargetException {
    	
    	List<MenuItemIdDTO> menuItemsDTO = new ArrayList<MenuItemIdDTO>();
    	
    	for (int i = 0; i < getMenuItems().size(); i++) {
    		MenuItemIdDTO temp = new MenuItemIdDTO();
        	BeanUtils.copyProperties(temp, getMenuItems().get(i));
        	menuItemsDTO.add(temp);
		}
        return(menuItemsDTO);
    }
    
    public MenuItemDetailDTO getMenuItem(int id) 
    		throws IllegalAccessException, InvocationTargetException
    {
    	MenuItemDetailDTO miDto = new MenuItemDetailDTO();
    	BeanUtils.copyProperties(miDto,getMenuItems().get(id));
    	return miDto;
    	
    }

	public void ChangePrice(MenuItemIdPriceDTO mi) {
		if(mi.getId() > getMenuItems().size())
			throw new ArrayIndexOutOfBoundsException();
		else{
			getMenuItems().get(mi.getId()).setPrice_per_person(mi.getPrice_per_person());
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date dateobj = new Date();
			getMenuItems().get(mi.getId()).setLast_modified_date(df.format(dateobj));
		}
	}
	
	public static AdminSchrDTO getSurcharge()
	{
		AdminSchrDTO sc = new AdminSchrDTO();
		sc.setSurcharge(surcharge);
		return sc;
	}

	public void setSurcharge(AdminSchrDTO scr) {
		MenuManager.surcharge = scr.getSurcharge();
	}
	
	public float getPrice(int menuId)
	{
		return getMenuItems().get(menuId).getPrice_per_person();
	}
}
