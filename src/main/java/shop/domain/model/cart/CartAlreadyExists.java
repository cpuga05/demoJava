package shop.domain.model.cart;

public final class CartAlreadyExists extends Exception {
    public CartAlreadyExists(CartId id) {
        super("The cart " + id.id() + " already exists.");
    }
}
