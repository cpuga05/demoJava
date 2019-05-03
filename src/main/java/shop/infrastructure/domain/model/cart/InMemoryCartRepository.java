package shop.infrastructure.domain.model.cart;

import shop.domain.model.cart.Cart;
import shop.domain.model.cart.CartId;
import shop.domain.model.cart.CartRepository;

import java.util.HashMap;

public class InMemoryCartRepository implements CartRepository {
    private final HashMap<String, Cart> carts;

    public InMemoryCartRepository() {
        this.carts = new HashMap<String, Cart>();
    }

    @Override
    public void save(Cart cart) {
        this.carts.put(cart.id().id(), cart);
    }

    @Override
    public Cart ofId(CartId id) {
        if (!this.carts.containsKey(id.id())) {
            return null;
        }

        return this.carts.get(id.id());
    }
}
