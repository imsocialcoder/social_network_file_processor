package org.example.processor;

import org.example.filewriter.EventFileWriter;
import org.example.model.EventType;
import org.example.model.SocialNetworkEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class EventProcessorImpl implements EventProcessor {
    private final Map<String, Map<EventType, Long>> eventMap;
    private final EventFileWriter fileWriter;
    public EventProcessorImpl(Map<String, Map<EventType, Long>> eventMap, EventFileWriter fileWriter) {
        this.eventMap = eventMap;
        this.fileWriter = fileWriter;
    }

    @Override
    public void processEvent(String fileName, SocialNetworkEvent event) throws IOException {
        eventMap.computeIfAbsent(event.postId(), k -> new HashMap<>())
                .merge(event.eventType(), event.count(), Long::sum);
        if (!fileName.contains("events.csv")){
            fileWriter.writeToFile(new SocialNetworkEvent(event.postId(), event.eventType(), eventMap.get(event.postId()).get(event.eventType())));
        }
    }
}

