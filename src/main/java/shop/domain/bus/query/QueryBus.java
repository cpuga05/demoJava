package shop.domain.bus.query;

public interface QueryBus {
    public void bind(String query, QueryHandler queryHandler);

    public <R, Q extends Query> R ask(Q query) throws Exception;
}
