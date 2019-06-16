package fi.haagahelia.Note;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.Note.domain.Note;
import fi.haagahelia.Note.domain.NoteRepository;
import fi.haagahelia.Note.domain.Tag;
import fi.haagahelia.Note.domain.TagRepository;
import fi.haagahelia.Note.domain.User;
import fi.haagahelia.Note.domain.UserRepository;


@SpringBootApplication
public class NoteApplication {
	public static void main(String[] args) {
		SpringApplication.run(NoteApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner note(NoteRepository noteRepository, TagRepository tagRepository, UserRepository urepository) {
		return (args) -> {
						
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			

		};
	}
}
