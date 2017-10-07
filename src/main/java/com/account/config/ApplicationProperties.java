package com.account.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties are configured in the application.yml file.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String fileLocation;

	public String getFileLocation() {
		return fileLocation;
	}

    
}
