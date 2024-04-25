package org.example.filereader;

import org.example.model.EventType;
import org.example.model.SocialNetworkEvent;
import org.example.processor.EventProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

class V1InputFileReaderTest {

    @Test
    public void testProcessInputFile() throws IOException {
        // Mock EventProcessor
        EventProcessor eventProcessor = Mockito.mock(EventProcessor.class);

        // Create instance of V1InputFileReader
        V1InputFileReader inputFileReader = new V1InputFileReader();

        // Call the method to be tested
        inputFileReader.processInputFile("v1_input_test.csv", eventProcessor);

        // Verify that processEvent was called with correct parameters
        verify(eventProcessor, times(1)).processEvent(eq("v1_input_test.csv"),
                eq(new SocialNetworkEvent("postId1", EventType.LIKE, 10)));
        verify(eventProcessor, times(1)).processEvent(eq("v1_input_test.csv"),
                eq(new SocialNetworkEvent("postId2", EventType.UNLIKE, 20)));

    }

    @Test
    public void testProcessInputFile_InvalidFormat_SkipsProcessing() throws IOException {
        // Mock EventProcessor
        EventProcessor eventProcessor = Mockito.mock(EventProcessor.class);

        // Create instance of V1InputFileReader
        V1InputFileReader inputFileReader = new V1InputFileReader();

        // Call the method to be tested
        inputFileReader.processInputFile("v1_input_invalid_test.csv", eventProcessor);

        // Verify that processEvent was not called
        verify(eventProcessor, never()).processEvent(anyString(), any(SocialNetworkEvent.class));
    }
}