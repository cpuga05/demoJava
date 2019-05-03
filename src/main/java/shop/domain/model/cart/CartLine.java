package shop.domain.model.cart;

import com.sun.javaws.exceptions.InvalidArgumentException;
import shop.domain.model.product.ProductId;
import shop.domain.model.shared.Currency;
import shop.domain.model.shared.Money;
import shop.domain.model.shared.Unit;

public final class CartLine {
    private static final int MAX_UNITS = 50;
    private CartId cartId;
    private ProductId productId;
    private Unit units;
    private Money unitPrice;
    private Unit unitsOffer;
    private Money offerPrice;
    private boolean offer;
    private Money totalPrice;
    private Money totalOfferPrice;

    public CartLine(CartId cartId, ProductId productId, Unit units, Money unitPrice, Unit unitsOffer, Money offerPrice) throws CartLineMaxUnits, InvalidArgumentException {
        this.assertNotMaxUnits(units);

        this.cartId = cartId;
        this.productId = productId;
        this.units = units;
        this.unitPrice = unitPrice;
        this.unitsOffer = unitsOffer;
        this.offerPrice = offerPrice;
        this.offer = false;
        this.totalPrice = new Money(0, new Currency("EUR"));
        this.totalOfferPrice = new Money(0, new Currency("EUR"));

        this.calculateTotalPrice();
    }

    private void assertNotMaxUnits(Unit units) throws CartLineMaxUnits {
        if (units.amount() > CartLine.MAX_UNITS) {
            throw new CartLineMaxUnits();
        }
    }

    public void takeMoreUnits(Unit units) throws CartLineMaxUnits, InvalidArgumentException {
        Unit aUnits = this.units.add(units);

        this.assertNotMaxUnits(aUnits);

        this.units = aUnits;

        this.calculateTotalPrice();
    }

    private void calculateTotalPrice() throws InvalidArgumentException {
        this.offer = false;
        this.totalPrice = this.unitPrice.multiply((double) this.units.amount());
        this.totalOfferPrice = new Money(0, new Currency("EUR"));

        if (this.units.isBiggerOrEqualThan(this.unitsOffer)) {
            this.offer = true;
            this.totalOfferPrice = this.offerPrice.multiply((double) this.units.amount());
        }
    }

    public String id() {
        return this.productId.id();
    }

    public CartId cartId() {
        return this.cartId;
    }

    public ProductId productId() {
        return this.productId;
    }

    public Unit units() {
        return this.units;
    }

    public Money unitPrice() {
        return this.unitPrice;
    }

    public Unit unitsOffer() {
        return this.unitsOffer;
    }

    public Money offerPrice() {
        return this.offerPrice;
    }

    public boolean offer() {
        return this.offer;
    }

    public Money totalPrice() {
        return this.totalPrice;
    }

    public Money totalOfferPrice() {
        return this.totalOfferPrice;
    }
}
