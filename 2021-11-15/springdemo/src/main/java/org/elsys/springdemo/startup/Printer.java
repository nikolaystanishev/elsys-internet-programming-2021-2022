package org.elsys.springdemo.startup;

import org.springframework.stereotype.Component;

@Component
public class Printer {
    public void print(String message) {
        System.out.println(message);
    }
}
