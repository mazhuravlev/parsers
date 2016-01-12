package com.company.queue;

import java.net.URL;
import java.util.ArrayList;

public class LinkQueue implements LinkQueueInterface {

    private ArrayList<URL> urls = new ArrayList<>();

    @Override
    public void push(URL url) {
        urls.add(url);
    }

    @Override
    public boolean hasRecords() {
        return urls.size() > 0;
    }

    @Override
    public URL pop() throws Exception {
        if(urls.size() > 0) {
            return urls.remove(urls.size() - 1);
        } else {
            throw new Exception("empty queue");
        }
    }
}
