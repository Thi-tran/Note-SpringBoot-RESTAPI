package fi.haagahelia.Note.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name ="note")
public class Note {
	
	@Id
	private String id;
	private String title, content; 
	
    @ManyToOne
    @JoinColumn(name = "tagId")
    private Tag tag;

	public Note () {}

	public Note(String title, String content, Tag tag) {
		super();
		this.title = title;
		this.content = content;
		this.tag = tag;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public void deleteTag() {
		this.tag = null;
	}
}
