package org.example;

import org.example.filereader.InputFileReader;
import org.example.filereader.V1InputFileReader;
import org.example.filereader.V2InputFileReader;
import org.example.processor.EventProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class SocialNetworkFileProcessorTest {
    @Test
    public void testProcessFiles() throws IOException {
        InputFileReader v1Reader = Mockito.mock(V1InputFileReader.class);
        InputFileReader v2Reader = Mockito.mock(V2InputFileReader.class);

        EventProcessor eventProcessor = Mockito.mock(EventProcessor.class);

        SocialNetworkFileProcessor fileProcessor = new SocialNetworkFileProcessor(v1Reader, v2Reader, eventProcessor);

        String[] fileNames = {"events.csv", "v1_input.csv", "v2_input.csv", "wrong.csv"};

        fileProcessor.processFiles(fileNames);

        verify(v1Reader, times(1)).processInputFile(eq("events.csv"), eq(eventProcessor));
        verify(v1Reader, times(1)).processInputFile(eq("v1_input.csv"), eq(eventProcessor));
        verify(v2Reader, times(1)).processInputFile(eq("v2_input.csv"), eq(eventProcessor));
    }

    @Test
    public void testMain() {
        Assertions.assertDoesNotThrow(() -> SocialNetworkFileProcessor.main(new String[0]));
    }
}