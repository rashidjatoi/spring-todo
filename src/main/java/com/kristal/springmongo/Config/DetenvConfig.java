package com.kristal.springmongo.Config;

import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DetenvConfig {
    public DetenvConfig() {
        Dotenv.configure().load();
    }
}
