package shop.domain.model.cart;

import shared.domain.event.DomainEvent;
import shop.domain.model.product.ProductId;

import java.time.LocalDateTime;

public final class CartProductTaken implements DomainEvent {
    private CartId cartId;
    private ProductId productId;
    private LocalDateTime occurredOn;

    public CartProductTaken(CartId cartId, ProductId productId) {
        this.cartId = cartId;
        this.productId = productId;
        this.occurredOn = LocalDateTime.now();
    }

    public CartId cartId() {
        return this.cartId;
    }

    @Override
    public LocalDateTime occurredOn() {
        return LocalDateTime.from(this.occurredOn);
    }
}
