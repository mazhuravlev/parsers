package com.company.avito;

import com.company.logger.LoggerInterface;
import com.company.queue.LinkQueue;
import com.company.queue.LinkQueueInterface;
import com.company.storage.OfferStorageInterface;

import java.net.MalformedURLException;
import java.net.URL;

public class AvitoLauncher {

    private LoggerInterface logger;
    private OfferStorageInterface storage;
    private URL baseUrl;

    public AvitoLauncher(OfferStorageInterface storage, LoggerInterface logger, URL url) {
        this.storage = storage;
        this.logger = logger;
        this.baseUrl = url;
    }

    public void run() throws MalformedURLException {
        final LinkQueueInterface linkQueue = new LinkQueue();
        final LinkGrabber linkGrabber = new LinkGrabber(logger, linkQueue);
        final OfferParser parser = new OfferParser(logger);
        final LinkGrabberRunner linkGrabberRunner = new LinkGrabberRunner(linkGrabber, logger);
        final ParserRunner parserRunner = new ParserRunner(parser, logger, linkQueue, storage);
        linkGrabber.setBaseUrl(baseUrl);
        new Thread(linkGrabberRunner).start();
        new Thread(parserRunner).start();
    }

}
