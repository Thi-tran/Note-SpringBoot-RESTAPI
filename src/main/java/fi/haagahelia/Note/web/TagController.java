package fi.haagahelia.Note.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.Note.domain.Note;
import fi.haagahelia.Note.domain.NoteRepository;
import fi.haagahelia.Note.domain.Tag;
import fi.haagahelia.Note.domain.TagRepository;

@Controller
public class TagController {
	
	@Autowired
	private NoteRepository repository;

	@Autowired
	private TagRepository tagRepository;
	
	// Create tag
	@RequestMapping(value="/addTag", method = RequestMethod.POST) 
	@ResponseBody
	public ResponseEntity createTag (@RequestBody Map<String, String> body) {
		String name = body.get("name");
		Tag tag = new Tag(name);
		try {
			tagRepository.save(tag);
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body("Created new tag");
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.NOT_ACCEPTABLE)
					.body(e);
		}

	}
	
	// Edit tag
	@RequestMapping(value="/editTag/{tagId}", method = RequestMethod.PUT) 
	@ResponseBody
	public ResponseEntity editTag(@PathVariable("tagId") String tagId, @RequestBody Map<String, String> body) {
		String name = body.get("name");
		try {
			Tag oldTag = tagRepository.findById(tagId).get();
			oldTag.setName(name);
			tagRepository.save(oldTag);
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body("Edit tag");
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.NOT_ACCEPTABLE)
					.body(e);
		} 
	}
	
	// Delete Tag
	@RequestMapping(value="/deleteTag/{tagId}", method = RequestMethod.DELETE)
	@ResponseBody
	public  ResponseEntity deleteTag(@PathVariable("tagId") String tagId) {
		try {
			tagRepository.deleteById(tagId);
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body("Deleted tag");
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.NOT_ACCEPTABLE)
					.body(e);
		} 
	}
	
	// Mapping/Edit Note to Tag 
	@RequestMapping(value="/assignTagToNote/{noteId}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity assignTagToNote(@PathVariable("noteId") String noteId, @RequestBody Map<String, String> body){
		String tagName = body.get("tagName");
		try {
			Note note = repository.findById(noteId).get();
			Tag tag = tagRepository.findByName(tagName).get(0);
			note.setTag(tag);
			repository.save(note);
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body("Mapping " + note.getTitle() + " to " + tag.getName());
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.NOT_ACCEPTABLE)
					.body(e);
		} 
	}
	
	// Detele Tag from Note 
	@RequestMapping(value="/DeleteTagFromNote/{noteId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity deleteTagFromNote(@PathVariable("noteId") String noteId) {
		try {
			Note note = repository.findById(noteId).get();
			note.deleteTag();
			repository.save(note);
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body("Deleted tag of  " + note.getTitle());
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.NOT_ACCEPTABLE)
					.body(e);
		} 
	}
}
