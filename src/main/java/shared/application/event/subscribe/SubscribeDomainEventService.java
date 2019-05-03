package shared.application.event.subscribe;

import shared.domain.event.DomainEventPublisher;
import shared.domain.event.DomainEventSubscriber;

public final class SubscribeDomainEventService {
    private final DomainEventPublisher domainEventPublisher;

    public SubscribeDomainEventService() {
        this.domainEventPublisher = DomainEventPublisher.getInstance();
    }

    public void execute(DomainEventSubscriber domainEventSubscriber) {
        this.domainEventPublisher.subscribe(domainEventSubscriber);
    }
}
