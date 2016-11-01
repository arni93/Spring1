package com.capgemini.chess.batch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchRunExample {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("batchConfiguration.xml");

	}
}
