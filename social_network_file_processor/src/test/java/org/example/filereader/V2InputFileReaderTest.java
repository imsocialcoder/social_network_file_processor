package org.example.filereader;

import org.example.model.EventType;
import org.example.model.SocialNetworkEvent;
import org.example.processor.EventProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class V2InputFileReaderTest {

    @Test
    public void testProcessInputFile_InvalidFormat_SkipsProcessing() throws IOException {
        // Mock EventProcessor
        EventProcessor eventProcessor = Mockito.mock(EventProcessor.class);

        // Create instance of V2InputFileReader
        V2InputFileReader inputFileReader = new V2InputFileReader();

        // Call the method to be tested
        inputFileReader.processInputFile("v2_input_invalid_test.csv", eventProcessor);

        // Verify that processEvent was not called for the invalid format line
        verify(eventProcessor, never()).processEvent(anyString(), any(SocialNetworkEvent.class));
    }

    @Test
    public void testProcessInputFile_ValidFormat_ProcessesEvent() throws IOException {
        // Mock EventProcessor
        EventProcessor eventProcessor = Mockito.mock(EventProcessor.class);

        // Create instance of V2InputFileReader
        V2InputFileReader inputFileReader = new V2InputFileReader();

        // Call the method to be tested
        inputFileReader.processInputFile("v2_input_test.csv", eventProcessor);

        // Verify that processEvent was called with the correct parameters for the valid format line
        verify(eventProcessor, times(1)).processEvent(eq("v2_input_test.csv"),
                eq(new SocialNetworkEvent("postId1", EventType.LIKE, 10)));
    }
}
