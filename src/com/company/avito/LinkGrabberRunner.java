package com.company.avito;

import com.company.logger.LoggerInterface;

public class LinkGrabberRunner implements Runnable {

    private LinkGrabber linkGrabber;
    private LoggerInterface logger;
    private int sleepTime = 60;

    public LinkGrabberRunner(LinkGrabber linkGrabber, LoggerInterface logger) {
        this.linkGrabber = linkGrabber;
        this.logger = logger;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        while (true){
            linkGrabber.grab();
            logger.info("link grabber sleeps for " + sleepTime, this);
            try {
                Thread.sleep(sleepTime * 1000);
            } catch (InterruptedException e) {

            }
        }
    }
}
