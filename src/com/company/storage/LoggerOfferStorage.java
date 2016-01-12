package com.company.storage;

import com.company.models.Offer;
import com.company.logger.LoggerInterface;

public class LoggerOfferStorage implements OfferStorageInterface {

    private LoggerInterface logger;

    public LoggerOfferStorage(LoggerInterface logger) {
        this.logger = logger;
    }

    @Override
    public void store(Offer offer) {
        logger.info("stored offer " + offer.hashCode(), this);
    }
}
