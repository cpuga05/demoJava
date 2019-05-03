package shop.domain.model.cart;

public final class CartLineMaxUnits extends Exception {
    public CartLineMaxUnits() {
        super("The cart line has reached its maximum number of units");
    }
}
