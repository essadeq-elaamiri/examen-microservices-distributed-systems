package me.elaamiri.events;

import lombok.Getter;

public class BaseEvent<IDType> {
    @Getter
    private IDType id;

    public BaseEvent(IDType id) {
        this.id = id;
    }
}
