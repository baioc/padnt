package baioc.dontpad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(Path.class)
@Table(name = "notepads")
public final class Notepad { // XXX: this could have been a record as well

	@Id private String directory;
	@Id private String filename;
	private String content;

	public Notepad(String directory, String filename, String content) {
		this.filename = filename;
		this.directory = directory;
		this.content = content;
	}

	protected Notepad() { this(null, null, null); }

	public String directory() { return directory; }
	public String filename() { return filename; }
	public String content() { return content; }

}
