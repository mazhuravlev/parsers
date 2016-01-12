package com.company.avito;

import com.company.logger.LoggerInterface;
import com.company.models.Offer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class OfferParser {

    private LoggerInterface logger;

    public OfferParser(LoggerInterface logger) {
        this.logger = logger;
    }

    public Offer parse(URL url) throws Exception {
        Document doc;
        try {
            doc = Jsoup.connect(url.toString()).get();
        } catch (IOException e) {
            logger.error("can not get " + url, this);
            throw new Exception();
        }
        Elements description = doc.select("div.description-preview-wrapper p");
        Offer offer = new Offer(url);
        if(description.size() > 0) {
            offer.text = description.text();
        } else {
            logger.error("invalid description format for " + url, this);
            throw new Exception();
        }
        logger.info("parsed offer at " + url, this);
        return offer;
    }

}
