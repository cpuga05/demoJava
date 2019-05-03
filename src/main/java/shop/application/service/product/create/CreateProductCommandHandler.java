package shop.application.service.product.create;

import com.sun.javaws.exceptions.InvalidArgumentException;
import shop.domain.bus.command.CommandHandler;
import shop.domain.model.product.ProductAlreadyExists;
import shop.domain.model.product.ProductId;
import shop.domain.model.shared.Money;
import shop.domain.model.shared.Unit;

public final class CreateProductCommandHandler implements CommandHandler<CreateProductCommand> {
    private CreateProductService createProductService;

    public CreateProductCommandHandler(CreateProductService createProductService) {
        this.createProductService = createProductService;
    }

    @Override
    public void handle(CreateProductCommand command) throws ProductAlreadyExists, InvalidArgumentException {
        ProductId id = new ProductId(command.id());
        String name = command.name();
        Money price = Money.fromString(command.price());
        Unit offerUnits = new Unit(command.offerUnits());
        Money offerPrice = Money.fromString(command.offerPrice());

        this.createProductService.execute(id, name, price, offerUnits, offerPrice);
    }
}
