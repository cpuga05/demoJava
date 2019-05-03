package shop.infrastructure.domain.bus.command;

import shop.domain.bus.command.Command;
import shop.domain.bus.command.CommandBus;
import shop.domain.bus.command.CommandHandler;

import java.util.HashMap;

public final class SyncCommandBus implements CommandBus {
    private final HashMap<String, CommandHandler> handlers;

    public SyncCommandBus() {
        this.handlers = new HashMap<>();
    }

    @Override
    public void bind(String command, CommandHandler commandHandler) {
        this.handlers.put(command, commandHandler);
    }

    @Override
    public <C extends Command> void dispatch(C command) throws Exception {
        String commandName = command.getClass().getName();

        if (!this.handlers.containsKey(commandName)) {
            throw new UnsupportedOperationException();
        }

        this.handlers.get(commandName).handle(command);
    }
}
