package com.jaakkomantyla.thebutton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * Main class of the button game.
 */
@SpringBootApplication
public class TheButtonApplication implements CommandLineRunner {


	/**
	 * The Push count repository saves situation of push counter.
	 */
	@Autowired
	PushCountRepository pushCountRepository;

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(TheButtonApplication.class, args);
	}

	/**
	 * Called when app starts. Checks if there is a counter already and creates one if not.
	 */
	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Optional<PushCounter> count = pushCountRepository.findById(1);
		if(!count.isPresent()){ pushCountRepository.save(new PushCounter()); }
	}
}
