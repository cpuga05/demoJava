package shop.domain.bus.command;

public interface CommandHandler<C extends Command> {
    public void handle(C command) throws Exception;
}
