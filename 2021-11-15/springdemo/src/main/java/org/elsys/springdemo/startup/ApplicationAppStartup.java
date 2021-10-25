package org.elsys.springdemo.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationAppStartup implements ApplicationRunner {

    @Autowired
    private Printer printer;

    @Autowired
    private PrefixPrinter prefixPrinter;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        printer.print("Hello from ApplicationAppStartup!");
        prefixPrinter.print("world");
    }
}
