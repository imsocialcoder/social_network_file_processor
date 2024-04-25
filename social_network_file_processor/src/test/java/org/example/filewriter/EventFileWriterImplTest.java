package org.example.filewriter;

import org.example.model.EventType;
import org.example.model.SocialNetworkEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventFileWriterImplTest {

    private EventFileWriterImpl eventFileWriter;

    @BeforeEach
    void setUp() {
        eventFileWriter = new EventFileWriterImpl();
    }

    @Test
    void testWriteToFile() throws IOException {
        String outputFile = "events.csv";
        SocialNetworkEvent event = new SocialNetworkEvent("postId", EventType.LIKE, 10);

        // Write event to file
        eventFileWriter.writeToFile(event);

        // Verify file content
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            String line;
            boolean eventFound = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(event.postId()) && parts[1].equals(event.eventType().getValue())) {
                    assertEquals(String.valueOf(event.count()), parts[2]);
                    eventFound = true;
                    break;
                }
            }
            assertTrue(eventFound);
        }
    }

    @Test
    void testWriteToFile_WhenEventExists() throws IOException {
        String outputFile = "events.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("postId1,Like,5\n");
            writer.write("postId2,Unlike,8\n");
        }

        // Create an event that already exists in the file
        SocialNetworkEvent event = new SocialNetworkEvent("postId1", EventType.LIKE, 15);

        // Write event to file
        eventFileWriter.writeToFile(event);

        // Verify the content of the output file
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            String line;
            boolean eventUpdated = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(event.postId()) && parts[1].equals(event.eventType().getValue())) {
                    assertEquals(String.valueOf(event.count()), parts[2]);
                    eventUpdated = true;
                    break;
                }
            }
            assertTrue(eventUpdated);
        }
    }

    @Test
    void testWriteToFile_WhenEventDoesNotExist() throws IOException {
        String outputFile = "events.csv";

        // Write some initial data to the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("postId1,Like,5\n");
            writer.write("postId2,Unlike,8\n");
        }

        // Create an event that does not exist in the file
        SocialNetworkEvent event = new SocialNetworkEvent("postId3", EventType.LIKE, 15);

        // Write event to file
        eventFileWriter.writeToFile(event);

        // Verify the content of the output file
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            String line;
            boolean eventAdded = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(event.postId()) && parts[1].equals(event.eventType().getValue())) {
                    assertEquals(String.valueOf(event.count()), parts[2]);
                    eventAdded = true;
                    break;
                }
            }
            assertTrue(eventAdded);
        }
    }
}
