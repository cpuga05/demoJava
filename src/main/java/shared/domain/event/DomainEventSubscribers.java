package shared.domain.event;

import shared.domain.Collection;

import java.util.List;

public final class DomainEventSubscribers extends Collection<DomainEventSubscriber> {
    public DomainEventSubscribers() {
    }

    public DomainEventSubscribers(DomainEventSubscriber[] items) {
        super(items);
    }

    public DomainEventSubscribers(List<DomainEventSubscriber> items) {
        super(items);
    }
}
