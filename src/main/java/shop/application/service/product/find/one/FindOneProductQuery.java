package shop.application.service.product.find.one;

import shop.domain.bus.query.Query;

import java.util.UUID;

public final class FindOneProductQuery extends Query {
    private final String id;

    public FindOneProductQuery(UUID queryId, String id) {
        super(queryId);

        this.id = id;
    }

    public String id() {
        return this.id;
    }
}
