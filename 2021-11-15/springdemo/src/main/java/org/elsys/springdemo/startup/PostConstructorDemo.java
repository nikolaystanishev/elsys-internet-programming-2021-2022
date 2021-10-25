package org.elsys.springdemo.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PostConstructorDemo {

    private Printer printer;

    @Autowired
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    @PostConstruct
    public void run() {
        printer.print("Hello from PostConstructorDemo!");
    }
}
