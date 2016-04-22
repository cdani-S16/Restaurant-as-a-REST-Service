package delectable.test.logic;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import delectable.dto.AdminSchrDTO;
import delectable.dto.CategoriesDTO;
import delectable.dto.IdDTO;
import delectable.dto.MenuItemDTO;
import delectable.dto.MenuItemIdPriceDTO;
import delectable.logic.MenuManager;

public class MenuManagerTest {

	MenuItemDTO mDTO;
	IdDTO a,b,c;
	
	@Before
	public void makeItem()
	{
		mDTO = new MenuItemDTO();
		mDTO.setMinimum_order(6);
		mDTO.setName("Random food");
		mDTO.setCategories(new ArrayList<CategoriesDTO>());
		mDTO.setPrice_per_person(4);
		
	}
	
	@Test
	public void testAddItem() throws IllegalAccessException, InvocationTargetException {
		
		a = MenuManager.menu.AddItem(mDTO);
		b = MenuManager.menu.AddItem(mDTO);
		c = MenuManager.menu.AddItem(mDTO);
		assertEquals(MenuManager.menu.getAllMenuItems().size(),3);
		assertEquals(MenuManager.menu.getMenuItem(a.getId()).getId(), a.getId());
		assertEquals(MenuManager.menu.getMenuItem(b.getId()).getId(), b.getId());
	}


	@Test
	public void testChangePrice() throws IllegalAccessException, InvocationTargetException {
		a = MenuManager.menu.AddItem(mDTO);
		b = MenuManager.menu.AddItem(mDTO);
		c = MenuManager.menu.AddItem(mDTO);
		
		MenuItemIdPriceDTO testItem = new MenuItemIdPriceDTO();
		testItem.setId(0);
		testItem.setPrice_per_person(6);
		MenuManager.menu.ChangePrice(testItem);
		boolean compare = false;
		if(MenuManager.menu.getPrice(0) == 6)
			compare = true;
		assertEquals(compare, true);
		if(!(MenuManager.menu.getPrice(a.getId()) == 4))
			compare = true;
		else if(!(MenuManager.menu.getPrice(c.getId()) == 4))
			compare = true;
		assertEquals(compare, true);
	}

	@Test
	public void testSurcharge() {
		//System.out.println(MenuManager.getSurcharge().getSurcharge());
		boolean compare = false;
		if(MenuManager.getSurcharge().getSurcharge() == 0)
			compare = true;
		assertEquals(compare, true);
		AdminSchrDTO scr = new AdminSchrDTO();
		scr.setSurcharge(2);
		MenuManager.menu.setSurcharge(scr);
		if(MenuManager.getSurcharge().getSurcharge() == 2)
			compare = true;
		assertEquals(compare, true);
	}

}
