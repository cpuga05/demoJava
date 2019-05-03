package shop.application.service.cart.take;

import shop.domain.bus.command.CommandHandler;
import shop.domain.model.cart.CartId;

public final class TakeCartCommandHandler implements CommandHandler<TakeCartCommand> {
    private TakeCartService takeCartService;

    public TakeCartCommandHandler(TakeCartService takeCartService) {
        this.takeCartService = takeCartService;
    }

    @Override
    public void handle(TakeCartCommand command) throws Exception {
        CartId id = new CartId(command.id());

        this.takeCartService.execute(id);
    }
}
