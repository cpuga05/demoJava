package shop.domain.model.cart;

import com.sun.javaws.exceptions.InvalidArgumentException;
import shared.domain.event.DomainEventPublisher;
import shop.domain.model.product.ProductId;
import shop.domain.model.product.ProductNotExists;
import shop.domain.model.shared.Currency;
import shop.domain.model.shared.Money;
import shop.domain.model.shared.Unit;

public final class Cart {
    private static final int MAX_LINES = 10;

    private CartId id;
    private CartLines lines;
    private Money totalPriceWithoutOffers;
    private Money totalPriceWithOffers;

    public Cart(CartId id) throws InvalidArgumentException {
        this.id = id;
        this.lines = new CartLines();
        this.totalPriceWithoutOffers = new Money(0, new Currency("EUR"));
        this.totalPriceWithOffers = new Money(0, new Currency("EUR"));

        DomainEventPublisher.getInstance().publish(new CartTaken(this.id()));
    }

    public void takeProduct(ProductId productId, Unit units, Money unitPrice, Unit unitsOffer, Money offerPrice) throws CartIsFull, CartLineMaxUnits, InvalidArgumentException {
        this.ensureCartNotIsFull();

        CartLine line = this.lines.findLine(productId);

        if (line != null) {
            line.takeMoreUnits(units);
        } else {
            this.lines.add(new CartLine(this.id(), productId, units, unitPrice, unitsOffer, offerPrice));
        }

        this.calculateTotalPrice();

        DomainEventPublisher.getInstance().publish(new CartProductTaken(this.id(), productId));
    }

    private void ensureCartNotIsFull() throws CartIsFull {
        if (this.lines.size() >= Cart.MAX_LINES) {
            throw new CartIsFull();
        }
    }

    public void removeProduct(ProductId productId) throws ProductNotExists, InvalidArgumentException {
        this.lines.removeLine(productId);
        this.calculateTotalPrice();
    }

    private void calculateTotalPrice() throws InvalidArgumentException {
        this.totalPriceWithoutOffers = this.totalPriceWithOffers = new Money(0, new Currency(("EUR")));

        for (CartLine line : this.lines) {
            this.totalPriceWithoutOffers = this.totalPriceWithoutOffers.add(line.totalPrice());

            if (line.offer()) {
                this.totalPriceWithOffers = this.totalPriceWithOffers.add(line.totalOfferPrice());
            } else {
                this.totalPriceWithOffers = this.totalPriceWithOffers.add(line.totalPrice());
            }
        }
    }

    public CartId id() {
        return this.id;
    }

    public CartLines lines() {
        return this.lines;
    }

    public Money totalPriceWithoutOffers() {
        return this.totalPriceWithoutOffers;
    }

    public Money totalPriceWithOffers() {
        return this.totalPriceWithOffers;
    }
}
