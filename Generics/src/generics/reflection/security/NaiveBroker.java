package generics.reflection.security;

import java.util.ArrayList;
import java.util.List;

//Without checking the list an unchecked orders might get by.
public class NaiveBroker {
	public void connect(OrderSupplier supplier, OrderProcessor processor) {
		List<AuthenticatedOrder> orders = new ArrayList<>();
		supplier.addOrders(orders);
		processor.processOrders(orders);
	}
}
