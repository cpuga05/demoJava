package shop.application.service.cart.takeproduct;

import com.sun.javaws.exceptions.InvalidArgumentException;
import shop.domain.model.cart.*;
import shop.domain.model.product.*;
import shop.domain.model.shared.Unit;

public final class TakeProductCartService {
    private CartRepository cartRepository;
    private CartFinder cartFinder;
    private ProductFinder productFinder;

    public TakeProductCartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartFinder = new CartFinder(cartRepository);
        this.productFinder = new ProductFinder(productRepository);
    }

    public void execute(CartId cartId, ProductId productId, Unit units) throws CartNotExists, ProductNotExists, CartIsFull, CartLineMaxUnits, InvalidArgumentException {
        Cart cart = this.cartFinder.execute(cartId);
        Product product = this.productFinder.execute(productId);

        cart.takeProduct(productId, units, product.price(), product.offerUnits(), product.offerPrice());

        this.cartRepository.save(cart);
    }
}
