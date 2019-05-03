package shop.domain.model.cart;

public final class CartFinder {
    private CartRepository cartRepository;

    public CartFinder(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart execute(CartId id) throws CartNotExists {
        Cart cart = this.cartRepository.ofId(id);

        if(cart == null) {
            throw new CartNotExists(id);
        }

        return cart;
    }
}
