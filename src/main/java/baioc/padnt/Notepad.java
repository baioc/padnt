package baioc.padnt;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(Path.class)
@Table(name = "notepads")
public class Notepad {

	@Id private String directory;
	@Id private String filename;
	private String content;

	public Notepad(String directory, String filename, String content) {
		this.directory = directory;
		this.filename = filename;
		this.content = content;
	}

	public Notepad(Path path, String content) {
		this(path.directory(), path.filename(), content);
	}

	protected Notepad() { this(null, null, null); }

	public String directory() { return directory; }
	public String filename() { return filename; }
	public String content() { return content; }

}
