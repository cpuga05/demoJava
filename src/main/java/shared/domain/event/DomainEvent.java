package shared.domain.event;

import java.time.LocalDateTime;

public interface DomainEvent {
    public LocalDateTime occurredOn();
}
