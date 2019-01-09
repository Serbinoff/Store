package com.griddynamics.Store;

import com.griddynamics.Store.model.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StoreApplication {

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public List<Item> cart() {
		return new ArrayList<>();
	}

	public static void main(String[] args) {

		SpringApplication.run(StoreApplication.class, args);

	}

}

