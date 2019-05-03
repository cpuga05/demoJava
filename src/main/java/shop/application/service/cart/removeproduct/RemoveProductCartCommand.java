package shop.application.service.cart.removeproduct;

import shop.domain.bus.command.Command;

import java.util.UUID;

public final class RemoveProductCartCommand extends Command {
    private String cartId;
    private String productId;

    public RemoveProductCartCommand(UUID commandId, String cartId, String productId) {
        super(commandId);

        this.cartId = cartId;
        this.productId = productId;
    }

    public String cartId() {
        return this.cartId;
    }

    public String productId() {
        return this.productId;
    }
}
