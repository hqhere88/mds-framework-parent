package org.vaadin.example;

import com.vaadin.flow.component.dependency.NpmPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.http.HttpServletRequest;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@SuppressWarnings("serial")
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
@NpmPackage(value = "leaflet", version = "^1.7.1")
@NpmPackage(value = "@types/leaflet", version = "^1.5.23")
@ComponentScan(basePackages={"mds.framework.service","org.vaadin.example"})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
       // SpringApplication.run(Application.class, args);
        //  SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
      //  builder.headless(false).run(args);

        ApplicationContext applicationContext =
                SpringApplication.run(Application .class, args);
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}
