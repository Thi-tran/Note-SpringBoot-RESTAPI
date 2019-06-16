package fi.haagahelia.Note;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.Note.domain.Note;
import fi.haagahelia.Note.domain.NoteRepository;
import fi.haagahelia.Note.domain.Tag;
import fi.haagahelia.Note.domain.TagRepository;


@SpringBootApplication
public class NoteApplication {
	public static void main(String[] args) {
		SpringApplication.run(NoteApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner note(NoteRepository noteRepository, TagRepository tagRepository) {
		return (args) -> {
			
			Tag tag1 = new Tag("Urgent");
			Tag tag2 = new Tag("Normal");
			tagRepository.save(tag1);
			tagRepository.save(tag2);
			
			Note note1 = new Note("To-do-list", "Work on projects", tag1);
			Note note2 = new Note("Morning", "Exercise", tag2);
		
			noteRepository.save(note1);
			noteRepository.save(note2);
			
			
		};
	}
}
