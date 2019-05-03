package shop.application.service.cart.view;

import shop.domain.bus.query.ResponseCollection;
import shop.domain.model.cart.CartLine;
import shop.domain.model.cart.CartLines;

import java.util.ArrayList;
import java.util.List;

public final class CartLinesResponse extends ResponseCollection<CartLineResponse> {
    public CartLinesResponse() {
    }

    public CartLinesResponse(CartLineResponse[] items) {
        super(items);
    }

    public CartLinesResponse(List<CartLineResponse> items) {
        super(items);
    }

    public static CartLinesResponse fromCartLines(CartLines cartLines) {
        List<CartLineResponse> items = new ArrayList<>();

        for (CartLine cartLine : cartLines) {
            items.add(CartLineResponse.fromCartLine(cartLine));
        }

        return new CartLinesResponse(items);
    }
}
