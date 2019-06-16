package fi.haagahelia.Note;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.Note.domain.Note;
import fi.haagahelia.Note.domain.NoteRepository;
import fi.haagahelia.Note.domain.Tag;
import fi.haagahelia.Note.domain.TagRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureDataMongo
public class TagRepositoryTest {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private NoteRepository noteRepository;
    
    @Test
    public void createTag() {
    	Tag tag = new Tag("Test");
    	tagRepository.save(tag);
    	assertThat(tag.getId()).isNotNull();
    }  
    
    @Test
    public void editTag() {
    	Tag tag = tagRepository.findByName("Test").get(0);
    	tag.setName("Edit");
    	tagRepository.save(tag);
    	assertThat(tag.getName()).isEqualTo("Edit");
    }    
    
    @Test
    public void assignTagToNote() {
    	Tag tag = tagRepository.findByName("Edit").get(0);
    	Note note = noteRepository.findByTitle("Afternoon").get(0);
    	
    	note.setTag(tag);
    	noteRepository.save(note);
    	assertThat(note.getTag().getName()).isEqualTo("Edit");
    }
    
    @Test
    public void deleteTagFromNote() {
    	Note note = noteRepository.findByTitle("Afternoon").get(0);
		note.deleteTag();
    	noteRepository.save(note);
    	assertThat(note.getTag()).isNull();
    }
}
