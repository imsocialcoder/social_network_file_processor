package org.example.processor;


import org.example.model.SocialNetworkEvent;

import java.io.IOException;

public interface EventProcessor {
    void processEvent(String fileName, SocialNetworkEvent event) throws IOException;
}

