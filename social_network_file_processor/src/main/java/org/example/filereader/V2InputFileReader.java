package org.example.filereader;

import org.example.model.EventType;
import org.example.model.SocialNetworkEvent;
import org.example.processor.EventProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class V2InputFileReader implements InputFileReader {

    @Override
    public void processInputFile(String filename, EventProcessor eventProcessor) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String postId = null;
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(",")) {
                    postId = line;
                } else {
                    String[] parts = line.split(",");
                    if (parts.length != 2) {
                        // Invalid format, skip this line
                        continue;
                    }
                    // Extract data from the parts
                    EventType eventType = EventType.fromString(parts[0]);
                    long count = Integer.parseInt(parts[1]);
                    // Process the event
                    eventProcessor.processEvent(filename, new SocialNetworkEvent(postId, eventType, count));
                }
            }
        }
    }
}

