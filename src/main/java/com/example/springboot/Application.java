package com.example.springboot;

import com.example.springboot.models.UserTableModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		UserTableModel.createFile();
		SpringApplication.run(Application.class, args);
	}
}
