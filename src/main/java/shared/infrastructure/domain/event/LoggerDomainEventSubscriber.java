package shared.infrastructure.domain.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringMapMessage;
import shared.domain.event.DomainEvent;
import shared.domain.event.DomainEventSubscriber;
import shop.domain.model.product.ProductCreated;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public final class LoggerDomainEventSubscriber implements DomainEventSubscriber {
    private final Logger logger;

    public LoggerDomainEventSubscriber() {
        this.logger = LogManager.getLogger(LoggerDomainEventSubscriber.class);
    }

    @Override
    public void handle(DomainEvent domainEvent) {
        Map<String, String> event = serialize(domainEvent);

        event.put("name", domainEvent.getClass().getName());

        this.logger.info(new StringMapMessage(event));
    }

    @Override
    public boolean isSubscribedTo(DomainEvent domainEvent) {
        return true;
    }

    private Map<String, String> serialize(DomainEvent domainEvent) {
        Map<String, String> myObjectAsDict = new HashMap<>();
        Field[] allFields = domainEvent.getClass().getDeclaredFields();

        try {
            for (Field field : allFields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                myObjectAsDict.put(field.getName(), field.get(domainEvent).toString());
            }
        } catch (Exception ignored) {
        }

        return myObjectAsDict;
    }
}
