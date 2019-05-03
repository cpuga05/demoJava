package shop.application.service.cart.view;

import shop.domain.model.cart.*;

public final class ViewCartService {
    private final CartFinder cartFinder;

    public ViewCartService(CartRepository cartRepository) {
        this.cartFinder = new CartFinder(cartRepository);
    }

    public Cart execute(CartId id) throws CartNotExists {
        return this.cartFinder.execute(id);
    }
}
