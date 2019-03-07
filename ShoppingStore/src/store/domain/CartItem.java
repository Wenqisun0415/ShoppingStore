package store.domain;

public class CartItem {

	private Product product;
	private int quantity;
	@SuppressWarnings("unused")
	private double subTotal;
	
	public CartItem() {
		
	}

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubTotal() {
		return product.getStore_price()*quantity;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
}
