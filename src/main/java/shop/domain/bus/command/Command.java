package shop.domain.bus.command;

import java.util.UUID;

public abstract class Command {
    private UUID commandId;

    public Command(UUID commandId) {
        this.commandId = commandId;
    }

    public UUID commandId() {
        return this.commandId;
    }
}
