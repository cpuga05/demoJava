package shop.domain.model.cart;

public interface CartRepository {
    public void save(Cart product);

    public Cart ofId(CartId id);
}
