package org.example.model;

public enum EventType {
    LIKE("Like"),
    UNLIKE("Unlike");

    private final String value;

    EventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EventType fromString(String value) {
        for (EventType eventType : EventType.values()) {
            if (eventType.value.equalsIgnoreCase(value)) {
                return eventType;
            }
        }
        throw new IllegalArgumentException("Unknown event type: " + value);
    }
}
