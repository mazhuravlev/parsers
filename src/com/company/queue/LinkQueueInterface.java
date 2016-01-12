package com.company.queue;

import java.net.URL;

public interface LinkQueueInterface {

    void push(URL url);
    URL pop() throws Exception;
    boolean hasRecords();

}
