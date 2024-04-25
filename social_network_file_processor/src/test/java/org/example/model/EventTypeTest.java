package org.example.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTypeTest {

    @Test
    public void testFromValidString() {
        assertEquals(EventType.LIKE, EventType.fromString("Like"));
        assertEquals(EventType.UNLIKE, EventType.fromString("Unlike"));
    }

    @Test
    public void testFromInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> EventType.fromString("InvalidType"));
        assertThrows(IllegalArgumentException.class, () -> EventType.fromString(""));
        assertThrows(IllegalArgumentException.class, () -> EventType.fromString(null));
    }

    @Test
    public void testGetValue() {
        assertEquals("Like", EventType.LIKE.getValue());
        assertEquals("Unlike", EventType.UNLIKE.getValue());
    }
}