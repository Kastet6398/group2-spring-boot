package com.example.springboot;

import com.example.springboot.models.UserTableModel;
import com.example.springboot.utils.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		if (!new File(Constants.USER_TABLE_FILE).exists())
			UserTableModel.createFile();
		SpringApplication.run(Application.class, args);
	}
}
