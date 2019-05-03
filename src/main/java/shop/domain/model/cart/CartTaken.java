package shop.domain.model.cart;

import shared.domain.event.DomainEvent;

import java.time.LocalDateTime;

public final class CartTaken implements DomainEvent {
    private CartId id;
    private LocalDateTime occurredOn;

    public CartTaken(CartId id) {
        this.id = id;
        this.occurredOn = LocalDateTime.now();
    }

    public CartId id() {
        return this.id;
    }

    @Override
    public LocalDateTime occurredOn() {
        return LocalDateTime.from(this.occurredOn);
    }
}
