package org.example.filereader;


import org.example.processor.EventProcessor;

import java.io.IOException;

public interface InputFileReader {
    void processInputFile(String filename, EventProcessor eventProcessor) throws IOException;
}
