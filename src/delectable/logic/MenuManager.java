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
	

	private static List<MenuItem> menuItems = new ArrayList<MenuItem>();
	public static MenuManager menu = new MenuManager();
	private static float surcharge = 0;

	public MenuManager()
	{
		//menuItems = new ArrayList<MenuItem>();
	}
	
	public IdDTO AddItem(MenuItemDTO item) throws IllegalAccessException, InvocationTargetException
	{
		IdDTO idObj = new IdDTO();
		MenuItem mi = new MenuItem();
		BeanUtils.copyProperties(mi, item);
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date dateobj = new Date();
		//System.out.println(df.format(dateobj));
		mi.setCreate_date(df.format(dateobj));
		mi.setLast_modified_date(df.format(dateobj));
		menuItems.add(mi);

		idObj.setId(mi.getId());
		return idObj;
		
	}
	
    public List<MenuItemIdDTO> getAllMenuItems() throws IllegalAccessException, InvocationTargetException {
    	
    	//Mapper mapper = new DozerBeanMapper();
    	List<MenuItemIdDTO> menuItemsDTO = new ArrayList<MenuItemIdDTO>();
    	
    	
    	for (int i = 0; i < menuItems.size(); i++) {

    		MenuItemIdDTO temp = new MenuItemIdDTO();
        	BeanUtils.copyProperties(temp, menuItems.get(i));
        	menuItemsDTO.add(temp);
		}

        return(menuItemsDTO);
    }
    
    public MenuItemDetailDTO getMenuItem(int id) throws IllegalAccessException, InvocationTargetException
    {
    	MenuItemDetailDTO miDto = new MenuItemDetailDTO();
    	BeanUtils.copyProperties(miDto,menuItems.get(id));
    	//BeanUtils.copyProperties(miDto.categories, menuItems.get(id).getCategories());
    	return miDto;
    	
    }

	public void ChangePrice(MenuItemIdPriceDTO mi) {
		if(mi.getId() > menuItems.size())
			throw new ArrayIndexOutOfBoundsException();
		else{
			menuItems.get(mi.getId()).setPrice_per_person(mi.getPrice_per_person());
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date dateobj = new Date();
			menuItems.get(mi.getId()).setLast_modified_date(df.format(dateobj));
		}
	}
	
	public static AdminSchrDTO getSurcharge()
	{
		AdminSchrDTO sc = new AdminSchrDTO();
		sc.setSurcharge(surcharge);
		return sc;
	}

	public void setSurcharge(AdminSchrDTO scr) {
		this.surcharge = scr.getSurcharge();
	}
	
	public float getPrice(int menuId)
	{
		return menuItems.get(menuId).getPrice_per_person();
	}
}
