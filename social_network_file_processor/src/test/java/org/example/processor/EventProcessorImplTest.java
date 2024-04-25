package org.example.processor;

import org.example.filewriter.EventFileWriter;
import org.example.model.EventType;
import org.example.model.SocialNetworkEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class EventProcessorImplTest {
    private EventProcessorImpl eventProcessor;
    private EventFileWriter mockFileWriter;

    @BeforeEach
    public void setUp() {
        mockFileWriter = Mockito.mock(EventFileWriter.class);
        eventProcessor = new EventProcessorImpl(new HashMap<>(), mockFileWriter);
    }

    @Test
    public void testProcessEvent_ExistingEvent() throws IOException {
        String postId = "post123";
        EventType eventType = EventType.LIKE;
        long count = 5;
        SocialNetworkEvent event = new SocialNetworkEvent(postId, eventType, count);

        // Call the method to be tested
        eventProcessor.processEvent("events.csv", event);

        // Verify that writeToFile is not called
        verify(mockFileWriter, never()).writeToFile(any());
    }

    @Test
    public void testProcessEvent_NewEvent() throws IOException {
        // Prepare test data
        String postId = "post456";
        EventType eventType = EventType.UNLIKE;
        long count = 10;
        SocialNetworkEvent event = new SocialNetworkEvent(postId, eventType, count);

        // Call the method to be tested
        eventProcessor.processEvent("test.csv", event);

        // Verify that the writeToFile is called
        verify(mockFileWriter, times(1)).writeToFile(event);
    }
}

