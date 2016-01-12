package com.company.logger;

public interface LoggerInterface {

    void info(String message, Object object);
    void error(String message, Object object);
    void notice(String message, Object object);

}
