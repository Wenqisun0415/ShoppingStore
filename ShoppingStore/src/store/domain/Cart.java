package store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<String, CartItem> cartItemMap = new HashMap<String, CartItem>();
	private Collection<CartItem> cartItemCollection;
	private double total;
	
	public Cart() {
		
	}
	
	public void addCartItem(CartItem cartItem) {
		String pid = cartItem.getProduct().getPid();
		if(cartItemMap.containsKey(pid)) {
			cartItemMap.get(pid).setQuantity(cartItemMap.get(pid).getQuantity()+cartItem.getQuantity());
		} else {
			cartItemMap.put(pid, cartItem);
		}
	}
	
	public void removeCartItem(String pid) {
		cartItemMap.remove(pid);
	}
	
	public void clearCart() {
		cartItemMap.clear();
	}

	public Map<String, CartItem> getcartItemMap() {
		return cartItemMap;
	}

	public void setcartItemMap(Map<String, CartItem> cartItemMap) {
		this.cartItemMap = cartItemMap;
	}

	public Collection<CartItem> getcartItemCollection() {
		return cartItemMap.values();
	}

	public void setcartItemCollection(Collection<CartItem> cartItemCollection) {
		this.cartItemCollection = cartItemCollection;
	}

	public double getTotal() {
		total = 0;
		cartItemCollection = this.getcartItemCollection();
		for (CartItem cartItem : cartItemCollection) {
			total += cartItem.getSubTotal();
		}
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
