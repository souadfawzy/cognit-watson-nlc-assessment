package com.cognit.watson.nlc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Souad
 * 
 * extending SpringBootServletInitializer to create a deployable war
 *
 */
@SpringBootApplication
public class CognitWatsonAssessmentApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CognitWatsonAssessmentApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CognitWatsonAssessmentApplication.class, args);
	}
}
