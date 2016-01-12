package com.company.logger;

import com.company.logger.LoggerInterface;

import java.time.Instant;

public class ConsoleLogger implements LoggerInterface {
    @Override
    public void info(String message, Object object) {
        System.out.println(timestamp() + " INFO(" + object.getClass() + "): " + message);
    }

    @Override
    public void error(String message, Object object) {
        System.out.println(timestamp() + " ERROR(" + object.getClass() + "): " + message);
    }

    @Override
    public void notice(String message, Object object) {
        System.out.println(timestamp() + " NOTICE(" + object.getClass() + "): " + message);
    }

    private String timestamp() {
        return Long.toString(Instant.now().getEpochSecond());
    }
}
