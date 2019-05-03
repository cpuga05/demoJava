package shop.application.service.cart.view;

import shop.domain.bus.query.Response;
import shop.domain.model.cart.CartLine;

public final class CartLineResponse implements Response {
    private String id;
    private String productId;
    private int units;
    private String unitPrice;
    private int unitsOffer;
    private String offerPrice;
    private boolean offer;
    private String totalPrice;
    private String totalOfferPrice;

    public CartLineResponse(String id, String productId, int units, String unitPrice, int unitsOffer, String offerPrice, boolean offer, String totalPrice, String totalOfferPrice) {
        this.id = id;
        this.productId = productId;
        this.units = units;
        this.unitPrice = unitPrice;
        this.unitsOffer = unitsOffer;
        this.offerPrice = offerPrice;
        this.offer = offer;
        this.totalPrice = totalPrice;
        this.totalOfferPrice = totalOfferPrice;
    }

    public static CartLineResponse fromCartLine(CartLine cartLine) {
        return new CartLineResponse(
                cartLine.id(),
                cartLine.productId().id(),
                cartLine.units().amount(),
                cartLine.unitPrice().toString(),
                cartLine.unitsOffer().amount(),
                cartLine.offerPrice().toString(),
                cartLine.offer(),
                cartLine.totalPrice().toString(),
                cartLine.totalOfferPrice().toString()
        );
    }

    public String id() {
        return this.id;
    }

    public String productId() {
        return this.productId;
    }

    public int units() {
        return this.units;
    }

    public String unitPrice() {
        return this.unitPrice;
    }

    public int unitsOffer() {
        return this.unitsOffer;
    }

    public String offerPrice() {
        return this.offerPrice;
    }

    public boolean offer() {
        return this.offer;
    }

    public String totalPrice() {
        return this.totalPrice;
    }

    public String totalOfferPrice() {
        return this.totalOfferPrice;
    }
}
