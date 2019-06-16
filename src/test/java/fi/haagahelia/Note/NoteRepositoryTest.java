package fi.haagahelia.Note;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.Note.domain.Note;
import fi.haagahelia.Note.domain.NoteRepository;
import fi.haagahelia.Note.domain.Tag;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureDataMongo
public class NoteRepositoryTest {
    @Autowired
    private NoteRepository repository;

    @Test
    public void findByTitleShouldReturnNote() {
        List<Note> notes = repository.findByTitle("Afternoon");
        
        assertThat(notes).hasSize(1);
        assertThat(notes.get(0).getContent()).isEqualTo("Out");
    }
    
    @Test
    public void createNewNote() {
    	Note note = new Note("Check", "Out", new Tag("Key"));
    	repository.save(note);
    	assertThat(note.getId()).isNotNull();
    }    
    
    @Test
    public void editNote() {
    	Note note = repository.findByTitle("Check").get(0);
    	
    	note.setTitle("Afternoon");
    	repository.save(note);
    	assertThat(note.getTitle()).isEqualTo("Afternoon");
    }
}
