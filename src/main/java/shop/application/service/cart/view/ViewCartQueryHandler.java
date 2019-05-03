package shop.application.service.cart.view;

import shop.domain.bus.query.QueryHandler;
import shop.domain.model.cart.CartId;

public final class ViewCartQueryHandler implements QueryHandler<CartResponse, ViewCartQuery> {
    private ViewCartService viewCartService;

    public ViewCartQueryHandler(ViewCartService viewCartService) {
        this.viewCartService = viewCartService;
    }

    @Override
    public CartResponse handle(ViewCartQuery query) throws Exception {
        CartId id = new CartId(query.id());

        return CartResponse.fromCart(this.viewCartService.execute(id));
    }
}
