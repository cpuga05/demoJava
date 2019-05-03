package shop.application.service.cart.view;

import shop.domain.bus.query.Query;

import java.util.UUID;

public final class ViewCartQuery extends Query {
    private String id;

    public ViewCartQuery(UUID queryId, String id) {
        super(queryId);

        this.id = id;
    }

    public String id() {
        return this.id;
    }
}
