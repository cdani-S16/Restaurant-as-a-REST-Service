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
		//add item and test the ids
		a = MenuManager.getMenu().AddItem(mDTO);
		b = MenuManager.getMenu().AddItem(mDTO);
		c = MenuManager.getMenu().AddItem(mDTO);
		assertEquals(MenuManager.getMenu().getAllMenuItems().size(),3);
		assertEquals(MenuManager.getMenu().getMenuItem(a.getId()).getId(), a.getId());
		assertEquals(MenuManager.getMenu().getMenuItem(b.getId()).getId(), b.getId());
		assertEquals(MenuManager.getMenu().getAllMenuItems().size(),3);
	}


	@Test
	public void testChangePrice() throws IllegalAccessException, InvocationTargetException {
		a = MenuManager.getMenu().AddItem(mDTO);
		b = MenuManager.getMenu().AddItem(mDTO);
		c = MenuManager.getMenu().AddItem(mDTO);
		
		MenuItemIdPriceDTO testItem = new MenuItemIdPriceDTO();
		testItem.setId(0);
		testItem.setPrice_per_person(6);
		MenuManager.getMenu().ChangePrice(testItem);
		boolean compare = false;
		if(MenuManager.getMenu().getPrice(0) == 6)
			compare = true;
		assertEquals(compare, true);
		if(!(MenuManager.getMenu().getPrice(a.getId()) == 4))
			compare = true;
		else if(!(MenuManager.getMenu().getPrice(c.getId()) == 4))
			compare = true;
		assertEquals(compare, true);
	}

	@Test
	public void testSurcharge() {
		boolean compare = false;
		if(MenuManager.getSurcharge().getSurcharge() == 0)
			compare = true;
		assertEquals(compare, true);
		AdminSchrDTO scr = new AdminSchrDTO();
		scr.setSurcharge(2);
		MenuManager.getMenu().setSurcharge(scr);
		if(MenuManager.getSurcharge().getSurcharge() == 2)
			compare = true;
		assertEquals(compare, true);
	}

}
