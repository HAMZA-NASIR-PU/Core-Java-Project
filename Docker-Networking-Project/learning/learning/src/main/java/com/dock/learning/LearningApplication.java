package com.dock.learning;

import com.dock.learning.models.User;
import com.dock.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LearningApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Insert some sample data into the database
		User user1 = new User();
		user1.setUsername("john_doe");
		user1.setEmail("john@example.com");

		User user2 = new User();
		user2.setUsername("michael_doe");
		user2.setEmail("michael@example.com");

		this.userRepository.save(user1);
		this.userRepository.save(user2);

		// Fetch all users from the database
		Iterable<User> users = this.userRepository.findAll();
		users.forEach(System.out::println);
	}

}
