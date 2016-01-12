package com.company;

import com.company.avito.AvitoLauncher;
import com.company.logger.ConsoleLogger;
import com.company.logger.LoggerInterface;
import com.company.storage.LoggerOfferStorage;
import com.company.storage.OfferStorageInterface;

import java.net.URL;

public class Main {

    public static void main(String[] args) throws Exception {
        LoggerInterface logger = new ConsoleLogger();
        OfferStorageInterface storage = new LoggerOfferStorage(logger);
        new AvitoLauncher(
                storage,
                logger,
                new URL("https://m.avito.ru/sevastopol/nedvizhimost")
        ).run();
    }

}
