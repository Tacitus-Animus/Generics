package generics.reflection.security;

import java.util.List;

//unchecked warning here means a naive broker has no way of knowing about the un-Authenticated Order.
public class DeviousSupplier implements OrderSupplier {

	@Override
	@SuppressWarnings("unchecked")
	public void addOrders(List<AuthenticatedOrder> orders) {
		@SuppressWarnings("rawtypes")
		List raw = orders;
		Order notAuthenticatedOrder = new Order();
		raw.add(notAuthenticatedOrder);
	}

}
