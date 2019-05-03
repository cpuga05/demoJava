package shop.application.service.cart.removeproduct;

import shop.domain.bus.command.CommandHandler;
import shop.domain.model.cart.CartId;
import shop.domain.model.product.ProductId;

public final class RemoveProductCartCommandHandler implements CommandHandler<RemoveProductCartCommand> {
    private RemoveProductCartService removeProductCartService;

    public RemoveProductCartCommandHandler(RemoveProductCartService removeProductCartService) {
        this.removeProductCartService = removeProductCartService;
    }

    @Override
    public void handle(RemoveProductCartCommand command) throws Exception {
        CartId cartId = new CartId(command.cartId());
        ProductId productId = new ProductId(command.productId());

        this.removeProductCartService.execute(cartId, productId);
    }
}
