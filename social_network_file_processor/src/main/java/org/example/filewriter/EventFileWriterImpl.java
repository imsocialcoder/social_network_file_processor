package org.example.filewriter;


import org.example.model.EventType;
import org.example.model.SocialNetworkEvent;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EventFileWriterImpl implements EventFileWriter {
    private final String outputFile = "events.csv";
    private final String tempFile = outputFile + ".tmp";
    @Override
    public void writeToFile(SocialNetworkEvent event) throws IOException {
        writeEventToFile(event.postId(), event.eventType(), event.count());
        replaceOriginalFile();
    }

    private void writeEventToFile(String postId, EventType eventType, long count)
            throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            boolean eventExists = false;
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(postId) && parts[1].equals(eventType.getValue())) {
                    // Event exists in file, replace with new count
                    writer.write(postId + "," + eventType.getValue() + "," + count + "\n");
                    eventExists = true;
                } else {
                    // Write existing line to temporary file
                    writer.write(line + "\n");
                }
            }

            if (!eventExists) {
                // Event does not exist in file, add new event
                writer.write(postId + "," + eventType.getValue() + "," + count + "\n");
            }
        }
    }

    private void replaceOriginalFile() throws IOException {
        Files.move(Paths.get(tempFile), Paths.get(outputFile), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }
}