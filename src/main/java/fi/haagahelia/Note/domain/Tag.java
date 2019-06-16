package fi.haagahelia.Note.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tag {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long tagId;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tag")
	List<Note> notes;
	
	public Tag() {}

	public Tag(String name) {
		super();
		this.name = name;
	}
	
	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
}
