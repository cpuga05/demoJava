package shop.application.service.cart.view;

import shop.domain.bus.query.Response;
import shop.domain.model.cart.Cart;

public final class CartResponse implements Response {
    private String id;
    private CartLinesResponse lines;
    private String totalPriceWithoutOffers;
    private String totalPriceWithOffers;

    public CartResponse(String id, CartLinesResponse lines, String totalPriceWithoutOffers, String totalPriceWithOffers) {
        this.id = id;
        this.lines = lines;
        this.totalPriceWithoutOffers = totalPriceWithoutOffers;
        this.totalPriceWithOffers = totalPriceWithOffers;
    }

    public static CartResponse fromCart(Cart cart) {
        return new CartResponse(
                cart.id().id(),
                CartLinesResponse.fromCartLines(cart.lines()),
                cart.totalPriceWithoutOffers().toString(),
                cart.totalPriceWithOffers().toString()
        );
    }

    public String id() {
        return this.id;
    }

    public CartLinesResponse lines() {
        return this.lines;
    }

    public String totalPriceWithoutOffers() {
        return this.totalPriceWithoutOffers;
    }

    public String totalPriceWithOffers() {
        return this.totalPriceWithOffers;
    }
}
