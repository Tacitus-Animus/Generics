package generics.reflection.security;

import java.util.List;

public interface OrderProcessor {
	public void processOrders(List<? extends Order> orders);
}
