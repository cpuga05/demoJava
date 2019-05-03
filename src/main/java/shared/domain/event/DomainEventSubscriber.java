package shared.domain.event;

public interface DomainEventSubscriber {
    public void handle(DomainEvent domainEvent);

    public boolean isSubscribedTo(DomainEvent domainEvent);
}
