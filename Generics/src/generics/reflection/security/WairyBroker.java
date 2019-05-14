
package generics.reflection.security;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class WairyBroker {
	public void connect(OrderSupplier supplier, OrderProcessor processor) {
		List<AuthenticatedOrder> orders = new ArrayList<>();
		//using checked list is one technique to enforce security.
		supplier.addOrders(Collections.checkedList(orders, AuthenticatedOrder.class));
		processor.processOrders(orders);
	
	}
}
