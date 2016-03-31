package delectable.pojo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import delectable.dto.*;

public class OrderManager {

	
	private static List<Order> Orders = new ArrayList<Order>();

    public List<Order> getAllOrders() {
        return(Orders);
    }

    public Order createOrder() {
    	Order o= new Order();
    	Orders.add(o);
        return(o);
    }

    public Order getOrderDetail(int oid) {
        return(findById(oid));
    }

    private Order findById(int lid) {
        Iterator<Order> oli = Orders.listIterator();
        while(oli.hasNext()) {
        	Order o = oli.next();
            if(o.matchesId(lid)) return(o);
        }
        return(new NullOrderDTO());
    }
}
