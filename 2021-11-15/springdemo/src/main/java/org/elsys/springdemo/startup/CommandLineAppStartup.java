package org.elsys.springdemo.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartup implements CommandLineRunner {

    private final Printer printer;

    @Autowired
    CommandLineAppStartup(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run(String... args) throws Exception {
        printer.print("Hello from CommandLineAppStartup!");
    }
}
