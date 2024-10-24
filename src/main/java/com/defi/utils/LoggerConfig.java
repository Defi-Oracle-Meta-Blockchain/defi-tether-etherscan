// Logger configuration for SLF4J and Logback
package com.defi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;

@Configuration
public class LoggerConfig {

    @Bean
    public Logger logger() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            context.reset(); // Reset any previous configuration
            configurator.doConfigure(getClass().getClassLoader().getResourceAsStream("logback.xml")); // Load configuration from logback.xml
        } catch (Exception e) {
            StatusPrinter.printInCaseOfErrorsOrWarnings(context);
        }
        return LoggerFactory.getLogger("com.defi");
    }
}
