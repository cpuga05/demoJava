package shop.application.service.product.create;

import shop.domain.bus.command.Command;

import java.util.UUID;

public final class CreateProductCommand extends Command {
    private String id;
    private String name;
    private String price;
    private int offerUnits;
    private String offerPrice;

    public CreateProductCommand(UUID commandId, String id, String name, String price, int offerUnits, String offerPrice) {
        super(commandId);
        this.id = id;
        this.name = name;
        this.price = price;
        this.offerUnits = offerUnits;
        this.offerPrice = offerPrice;
    }

    public String id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public String price() {
        return this.price;
    }

    public int offerUnits() {
        return this.offerUnits;
    }

    public String offerPrice() {
        return this.offerPrice;
    }
}
