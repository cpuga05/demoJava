package shop.application.service.cart.take;

import shop.domain.bus.command.Command;

import java.util.UUID;

public final class TakeCartCommand extends Command {
    private String id;

    public TakeCartCommand(UUID commandId, String id) {
        super(commandId);
        this.id = id;
    }

    public String id() {
        return this.id;
    }
}
