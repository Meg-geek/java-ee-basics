package com.nsu.basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(Application.class);

        log.info("Application is started");
        System.out.println("Hello world!");
        log.info("Hello world is printed");
    }
}
