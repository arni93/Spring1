package com.capgemini.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * runs application with spring boot
 * 
 * @author AWOZNICA
 */
@EnableScheduling
@SpringBootApplication
public class ChessApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChessApplication.class, args);
	}
}
