package generics.reflection.security;

import java.util.List;

public interface OrderSupplier {

	public void addOrders(List<AuthenticatedOrder> orders);
}
