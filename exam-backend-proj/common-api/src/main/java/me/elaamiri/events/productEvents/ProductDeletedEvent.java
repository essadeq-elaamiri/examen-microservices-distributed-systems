package me.elaamiri.events.productEvents;

import me.elaamiri.events.BaseEvent;

public class ProductDeletedEvent extends BaseEvent<String> {
    public ProductDeletedEvent(String id) {
        super(id);
    }
}
