package shop.application.service.cart.takeproduct;

import shop.domain.bus.command.CommandHandler;
import shop.domain.model.cart.CartId;
import shop.domain.model.product.ProductId;
import shop.domain.model.shared.Unit;

public final class TakeProductCartCommandHandler implements CommandHandler<TakeProductCartCommand> {
    private TakeProductCartService takeProductCartService;

    public TakeProductCartCommandHandler(TakeProductCartService takeProductCartService) {
        this.takeProductCartService = takeProductCartService;
    }

    @Override
    public void handle(TakeProductCartCommand command) throws Exception {
        CartId cartId = new CartId(command.cartId());
        ProductId productId = new ProductId(command.productId());
        Unit units = new Unit(command.units());

        this.takeProductCartService.execute(cartId, productId, units);
    }
}
