package com.example.chat_web;

import com.example.chat_web.model.User;
import com.example.chat_web.services.UserServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatWebApplication.class, args);
	}

    @Bean
	public CommandLineRunner commandLineRunner(
			UserServices userServices
){
		return args -> {
			userServices.register(User.builder()
					.username("Gian")
					.email("gian@gmail.com")
					.password("1234")
					.build());
			userServices.register(User.builder()
					.username("juan")
					.email("juan@gmail.com")
					.password("1234")
					.build());
			userServices.register(User.builder()
					.username("maria")
					.email("maria@gmail.com")
					.password("1234")
					.build());
		};
}

}
