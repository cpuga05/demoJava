package shop.domain.model.cart;

public final class CartNotExists extends Exception {
    public CartNotExists(CartId id) {
        super("The cart " + id.id() + " not exists.");
    }
}
