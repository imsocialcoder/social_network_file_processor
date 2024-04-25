package org.example.filewriter;


import org.example.model.SocialNetworkEvent;

import java.io.IOException;

public interface EventFileWriter {
    void writeToFile(SocialNetworkEvent event) throws IOException;
}
