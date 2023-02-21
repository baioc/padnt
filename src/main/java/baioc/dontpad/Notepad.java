package baioc.dontpad;

import java.io.Serializable;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(Notepad.Path.class)
@Table(name = "notepads")
public record Notepad(
	@Id String directory,
	@Id String filename,
	String content
) {

	public Notepad() { this("/", "", null); }

	public record Path(
		String directory,
		String filename
	) implements Serializable {
		public Path() { this("/", ""); }
	}

}
