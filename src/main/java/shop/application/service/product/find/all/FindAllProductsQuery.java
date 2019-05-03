package shop.application.service.product.find.all;

import shop.domain.bus.query.Query;

import java.util.UUID;

public final class FindAllProductsQuery extends Query {
    public FindAllProductsQuery(UUID queryId) {
        super(queryId);
    }
}
