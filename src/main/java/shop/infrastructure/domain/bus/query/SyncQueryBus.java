package shop.infrastructure.domain.bus.query;

import shop.domain.bus.query.Query;
import shop.domain.bus.query.QueryBus;
import shop.domain.bus.query.QueryHandler;

import java.util.HashMap;

public class SyncQueryBus implements QueryBus {
    private final HashMap<String, QueryHandler> handlers;

    public SyncQueryBus() {
        this.handlers = new HashMap<>();
    }

    @Override
    public void bind(String query, QueryHandler queryHandler) {
        this.handlers.put(query, queryHandler);
    }

    @Override
    public <R, Q extends Query> R ask(Q query) throws Exception {
        String commandName = query.getClass().getName();

        if (!this.handlers.containsKey(commandName)) {
            throw new UnsupportedOperationException();
        }

        return (R) this.handlers.get(commandName).handle(query);
    }
}
