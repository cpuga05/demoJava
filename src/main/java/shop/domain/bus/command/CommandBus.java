package shop.domain.bus.command;

public interface CommandBus {
    public void bind(String command, CommandHandler commandHandler);

    public <C extends Command> void dispatch(C command) throws Exception;
}
