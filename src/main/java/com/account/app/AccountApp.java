package com.account.app;

import java.net.UnknownHostException;

import javax.ws.rs.core.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class AccountApp extends Application {

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments
     * @throws UnknownHostException if the local host name could not be resolved into an address
     */
    public static void main(final String[] args) throws UnknownHostException {
        SpringApplication.run(AccountApp.class, args);
    }

}
