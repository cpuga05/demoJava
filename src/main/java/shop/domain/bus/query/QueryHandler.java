package shop.domain.bus.query;

public interface QueryHandler<R extends Response, Q extends Query> {
    public R handle(Q query) throws Exception;
}
