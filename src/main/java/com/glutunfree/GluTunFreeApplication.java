package com.glutunfree;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GluTunFreeApplication extends SpringBootServletInitializer {

	
	public static void main(String... args) {
		
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GluTunFreeApplication.class);
	}
	
	  @Bean
	  public ModelMapper modelMapper() {
	    return new ModelMapper();
	  }

}
