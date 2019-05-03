package shop.domain.bus.query;

import shared.domain.Collection;

import java.util.List;

public abstract class ResponseCollection<T> extends Collection<T> implements Response {
    public ResponseCollection() {
    }

    public ResponseCollection(T[] items) {
        super(items);
    }

    public ResponseCollection(List<T> items) {
        super(items);
    }
}
