package shop.domain.model.product;

import shared.domain.event.DomainEvent;

import java.time.LocalDateTime;

public final class ProductCreated implements DomainEvent {
    private ProductId id;
    private LocalDateTime occurredOn;

    public ProductCreated(ProductId id) {
        this.id = id;
        this.occurredOn = LocalDateTime.now();
    }

    public ProductId id() {
        return this.id;
    }

    @Override
    public LocalDateTime occurredOn() {
        return LocalDateTime.from(this.occurredOn);
    }
}
