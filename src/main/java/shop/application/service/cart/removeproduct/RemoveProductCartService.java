package shop.application.service.cart.removeproduct;

import com.sun.javaws.exceptions.InvalidArgumentException;
import shop.domain.model.cart.*;
import shop.domain.model.product.ProductFinder;
import shop.domain.model.product.ProductId;
import shop.domain.model.product.ProductNotExists;
import shop.domain.model.product.ProductRepository;

public final class RemoveProductCartService {
    private CartRepository cartRepository;
    private CartFinder cartFinder;
    private ProductFinder productFinder;

    public RemoveProductCartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartFinder = new CartFinder(cartRepository);
        this.productFinder = new ProductFinder(productRepository);
    }

    public void execute(CartId cartId, ProductId productId) throws ProductNotExists, CartNotExists, InvalidArgumentException {
        this.ensureProductExists(productId);

        Cart cart = this.cartFinder.execute(cartId);

        cart.removeProduct(productId);

        this.cartRepository.save(cart);
    }

    private void ensureProductExists(ProductId productId) throws ProductNotExists {
        this.productFinder.execute(productId);
    }
}
