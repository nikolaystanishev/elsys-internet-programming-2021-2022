package org.elsys.springdemo.startup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureDI {

    @Bean
    public PrefixPrinter getPrefixPrinter() {
        return new PrefixPrinter();
    }
}
