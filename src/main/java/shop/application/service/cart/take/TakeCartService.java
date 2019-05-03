package shop.application.service.cart.take;

import com.sun.javaws.exceptions.InvalidArgumentException;
import shop.domain.model.cart.Cart;
import shop.domain.model.cart.CartAlreadyExists;
import shop.domain.model.cart.CartId;
import shop.domain.model.cart.CartRepository;

public final class TakeCartService {
    private CartRepository cartRepository;

    public TakeCartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void execute(CartId id) throws CartAlreadyExists, InvalidArgumentException {
        if (this.cartRepository.ofId(id) != null) {
            throw new CartAlreadyExists(id);
        }

        Cart cart = new Cart(id);

        this.cartRepository.save(cart);
    }
}
