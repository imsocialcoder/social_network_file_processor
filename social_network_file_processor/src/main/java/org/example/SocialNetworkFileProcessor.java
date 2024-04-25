package org.example;

import org.example.filereader.InputFileReader;
import org.example.filereader.V1InputFileReader;
import org.example.filereader.V2InputFileReader;
import org.example.filewriter.EventFileWriterImpl;
import org.example.processor.EventProcessor;
import org.example.processor.EventProcessorImpl;

import java.io.IOException;
import java.util.HashMap;

public class SocialNetworkFileProcessor {
    private final InputFileReader v1Reader;
    private final InputFileReader v2Reader;
    private final EventProcessor eventProcessor;

    // Default constructor with default implementations of dependencies
    public SocialNetworkFileProcessor() {
        this.v1Reader = new V1InputFileReader();
        this.v2Reader = new V2InputFileReader();
        this.eventProcessor = new EventProcessorImpl(new HashMap<>(), new EventFileWriterImpl());
    }

    // Constructor with custom implementations of dependencies
    public SocialNetworkFileProcessor(InputFileReader v1Reader, InputFileReader v2Reader, EventProcessor eventProcessor) {
        this.v1Reader = v1Reader;
        this.v2Reader = v2Reader;
        this.eventProcessor = eventProcessor;
    }

    public void processFiles(String[] fileNames) throws IOException {
        for (String filename : fileNames) {
            InputFileReader reader;
            if (filename.contains("v1_") || filename.contains("events.csv")) {
                reader = v1Reader;
            } else if (filename.contains("v2_")) {
                reader = v2Reader;
            } else {
                // Handle unsupported file format
                System.out.println("Unsupported file format: " + filename);
                continue;
            }

            // Process the file
            reader.processInputFile(filename, eventProcessor);
        }
    }

    public static void main(String[] args) throws IOException {
        String[] fileNames = {"events.csv", "v1_input.csv", "v2_input.csv"};

        // Create an instance of SocialNetworkFileProcessor with default dependencies
        SocialNetworkFileProcessor fileProcessor = new SocialNetworkFileProcessor();

        // Process the files
        fileProcessor.processFiles(fileNames);
    }
}
