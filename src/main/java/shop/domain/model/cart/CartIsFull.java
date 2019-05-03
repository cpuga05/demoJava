package shop.domain.model.cart;

public final class CartIsFull extends Exception {
    public CartIsFull() {
        super("The cart is full");
    }
}
