package fi.haagahelia.Note.web;
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
public class NoteController {

	@Autowired
	private NoteRepository repository;
	
	@Autowired
	private TagRepository tagRepository;
	
	///
	// Template
	///
	
	// List note
	@RequestMapping(value="/", method = RequestMethod.GET) 
	public String listNote(Model model) {
		model.addAttribute("notes", repository.findAll());
		return "notelist";
	}
	
	@RequestMapping(value="/notelist", method = RequestMethod.GET) 
	public String listNote2(Model model) {
		model.addAttribute("notes", repository.findAll());
		return "notelist";
	}

	// Delete Note 
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteNote(@PathVariable("id") String noteId, Model model) {
    	repository.deleteById(noteId);
        return "redirect:../notelist";
    }     
    
	///
	// REST API 
	///
	
	// Create Note
	@RequestMapping(value="/addnote", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity createNote(@RequestBody Map<String, String> body) {
		String title = body.get("title");
		String content = body.get("content");
		String tagName = body.get("tagName");
		try {
			Tag tag = tagRepository.findByName(tagName).get(0); 
			Note note = new Note(title, content, tag);
			repository.save(note);
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body("Created new note");
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.NOT_ACCEPTABLE)
					.body(e);
		}
	}
	
	// Edit Note
	@RequestMapping(value="/edit/{noteId}", method= RequestMethod.PUT) 
	@ResponseBody
	public ResponseEntity updateNote(@PathVariable("noteId") String noteId, @RequestBody Map<String,String> body){
		String title = body.get("title");
		String content = body.get("content");
		Note oldNote = repository.findById(noteId).get();

		try {
			
			if (title.length()>0) {
				oldNote.setTitle(title);			
			}
			
			if (content.length()>0) {
				oldNote.setContent(content);			
			}
			
			repository.save(oldNote);
			
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body("Edited note");
		
		} catch (Exception e) {
			
			return ResponseEntity
				.status(HttpStatus.NOT_ACCEPTABLE)
				.body(e);
		}
	}
	
	// Delete Note 
	@RequestMapping(value="/delete/{noteId}", method= RequestMethod.DELETE) 
	@ResponseBody
	public ResponseEntity deleteNote(@PathVariable("noteId") String noteId){
		try {
			
			repository.deleteById(noteId);
			
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body("Edited note");
		
		} catch (Exception e) {
			
			return ResponseEntity
				.status(HttpStatus.NOT_ACCEPTABLE)
				.body(e);

		}
	}
}
