package com.company.avito;

import com.company.queue.LinkQueueInterface;
import com.company.logger.LoggerInterface;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LinkGrabber {

    private URL baseUrl;
    private LoggerInterface logger;
    private LinkQueueInterface linkQueue;

    public LinkGrabber(LoggerInterface logger, LinkQueueInterface linkQueue) {
        this.logger = logger;
        this.linkQueue = linkQueue;
    }

    public void setBaseUrl(URL url) throws MalformedURLException {
        baseUrl = url;
    }

    public void grab() {
        Document doc;
        try {
            doc = Jsoup.connect(baseUrl.toString()).get();
        } catch (IOException e) {
            logger.error("can not get " + baseUrl, this);
            return;
        }
        Elements a = doc.select("a.item-link[href]");
        logger.info("got " + a.size() + " links", this);
        a.forEach((element -> {
            try {
                URL url = new URL(baseUrl, element.attr("href"));
                linkQueue.push(url);
            } catch (MalformedURLException e) {
                logger.notice("invalid href: " + element.attr("href"), this);
            }
        }));
    }

}
