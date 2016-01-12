package com.company.avito;

import com.company.queue.LinkQueueInterface;
import com.company.logger.LoggerInterface;
import com.company.models.Offer;
import com.company.storage.OfferStorageInterface;

import java.net.URL;

public class ParserRunner implements Runnable {

    private final OfferParser offerParser;
    private final LoggerInterface logger;
    private final LinkQueueInterface linkQueue;
    private final OfferStorageInterface offerStorage;
    private int sleepTime = 3;

    public ParserRunner(OfferParser offerParser, LoggerInterface logger, LinkQueueInterface linkQueue, OfferStorageInterface offerStorage) {
        this.offerStorage = offerStorage;
        this.offerParser = offerParser;
        this.linkQueue = linkQueue;
        this.logger = logger;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        while (true) {
            if (linkQueue.hasRecords()) {
                URL offerUrl = null;
                try {
                    synchronized (linkQueue) {
                        offerUrl = linkQueue.pop();
                    }
                } catch (Exception e) {
                    logger.error("link queue error", this);
                }
                if(null != offerUrl) {
                    try {
                        Offer offer = offerParser.parse(offerUrl);
                        offerStorage.store(offer);
                    } catch (Exception e) {
                        logger.info("unable to parse " + offerUrl, this);
                    }
                }
            } else {
                logger.info("link queue is empty", this);
            }
            logger.info("offer parser runner sleeps for " + sleepTime, this);
            try {
                Thread.sleep(sleepTime * 1000);
            } catch (InterruptedException e) {}
        }
    }
}
