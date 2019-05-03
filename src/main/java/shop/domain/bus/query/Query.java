package shop.domain.bus.query;

import java.util.UUID;

public abstract class Query {
    private UUID queryId;

    public Query(UUID queryId) {
        this.queryId = queryId;
    }

    public UUID commandId() {
        return this.queryId;
    }
}
