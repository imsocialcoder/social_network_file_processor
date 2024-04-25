package org.example.filereader;


import org.example.model.EventType;
import org.example.model.SocialNetworkEvent;
import org.example.processor.EventProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class V1InputFileReader implements InputFileReader {

    @Override
    public void processInputFile(String filename, EventProcessor eventProcessor) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    // Invalid format, skip this line
                    continue;
                }
                // Extract data from the parts
                String postId = parts[0];
                EventType eventType = EventType.fromString(parts[1]);
                long count = Integer.parseInt(parts[2]);
                // Process the event
                eventProcessor.processEvent(filename, new SocialNetworkEvent(postId, eventType, count));
            }
        }
    }
}

