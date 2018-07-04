package com.mood.common.boot;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.mood"})
public class HelloOpenshiftApplication {

	public static void main(String[] args) {
	     SpringApplication sa = new SpringApplication();
	     sa.addListeners(new ApplicationListener<ApplicationEvent>() {
	    	 @Override
	    	 public void onApplicationEvent(ApplicationEvent event) {
	    		 System.out.println("event: " + event);
	    	 }
	     });
	     sa.addPrimarySources(new HashSet<>(Arrays.asList(HelloOpenshiftApplication.class)));
	     sa.run(args);
	}
}
