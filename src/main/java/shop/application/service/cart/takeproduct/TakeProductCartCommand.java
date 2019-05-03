package shop.application.service.cart.takeproduct;

import shop.domain.bus.command.Command;

import java.util.UUID;

public final class TakeProductCartCommand extends Command {
    private String cartId;
    private String productId;
    private int units;

    public TakeProductCartCommand(UUID commandId, String cartId, String productId, int units) {
        super(commandId);

        this.cartId = cartId;
        this.productId = productId;
        this.units = units;
    }

    public String cartId() {
        return this.cartId;
    }

    public String productId() {
        return this.productId;
    }

    public int units() {
        return this.units;
    }
}
