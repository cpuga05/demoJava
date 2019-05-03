package shop.domain.model.product;

import com.sun.javaws.exceptions.InvalidArgumentException;
import shared.domain.event.DomainEventPublisher;
import shop.domain.model.shared.Money;
import shop.domain.model.shared.Unit;

public final class Product {
    private ProductId id;
    private String name;
    private Money price;
    private Unit offerUnits;
    private Money offerPrice;

    public Product(ProductId id, String name, Money price, Unit offerUnits, Money offerPrice) throws InvalidArgumentException {
        this.id = id;
        this.price = price;
        this.offerUnits = offerUnits;
        this.offerPrice = offerPrice;

        this.rename(name);

        DomainEventPublisher.getInstance().publish(new ProductCreated(this.id()));
    }

    public void rename(String name) throws InvalidArgumentException {
        this.ensureNameIsNotNull(name);
        this.ensureNameIsNotEmpty(name);

        this.name = name;
    }

    private void ensureNameIsNotNull(String name) throws InvalidArgumentException {
        if (name == null) {
            throw new InvalidArgumentException(new String[]{"The name is null"});
        }
    }

    private void ensureNameIsNotEmpty(String name) throws InvalidArgumentException {
        if (name.length() == 0) {
            throw new InvalidArgumentException(new String[]{"The name is empty"});
        }
    }

    public ProductId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Money price() {
        return price;
    }

    public Unit offerUnits() {
        return offerUnits;
    }

    public Money offerPrice() {
        return offerPrice;
    }
}
