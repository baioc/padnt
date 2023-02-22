package baioc.padnt;

import java.io.Serializable;

// XXX: this should have been a record, but Spring sucks
public final class Path implements Serializable {

	private String directory;
	private String filename;

	public Path(String directory, String filename) {
		this.filename = filename;
		this.directory = directory;
	}

	protected Path() { this(null, null); }

	public String directory() { return directory; }
	public String filename() { return filename; }

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Path other) {
			return this.directory == other.directory
				&& this.filename == other.filename;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return directory.hashCode() - filename.hashCode();
	}

	@Override
	public String toString() {
		return directory + filename;
	}

}
