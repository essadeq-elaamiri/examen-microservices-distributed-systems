package me.elaamiri.events.categoryEvents;

import me.elaamiri.events.BaseEvent;

public class CategoryDeletedEvent extends BaseEvent<String> {
    public CategoryDeletedEvent(String id) {
        super(id);
    }
}
