package shared.domain.event;

public final class DomainEventPublisher {
    public static DomainEventPublisher instance;

    public static DomainEventPublisher getInstance() {
        if (instance == null) {
            instance = new DomainEventPublisher();
        }

        return instance;
    }

    private DomainEventSubscribers subscribers;

    public DomainEventPublisher() {
        this.subscribers = new DomainEventSubscribers();
    }

    public void subscribe(DomainEventSubscriber domainEventSubscriber) {
        this.subscribers.add(domainEventSubscriber);
    }

    public void unsubscribe(DomainEventSubscriber domainEventSubscriber) {
        this.subscribers.remove(domainEventSubscriber);
    }

    public void publish(DomainEvent domainEvent) {
        for (DomainEventSubscriber domainEventSubscriber : this.subscribers) {
            if (domainEventSubscriber.isSubscribedTo(domainEvent)) {
                domainEventSubscriber.handle(domainEvent);
            }
        }
    }
}
