package baioc.dontpad;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class NotepadService {

	private final NotepadRepository repository;

	NotepadService(NotepadRepository repository) {
		this.repository = repository;
	}

	public String read(Path path) {
		var directory = path.directory();
		var filename = path.filename();
		return repository
			.findByDirectoryAndFilename(directory, filename)
			.orElse(new Notepad(directory, filename, ""))
			.content();
	}

	/**
	 * Either creates a new notepad, or updates an existing one at the specified path.
	 * @return true if and only if a new entry was created by this upsert.
	 */
	public boolean upsert(Path path, String content) {
		boolean existed = repository.existsById(path);
		repository.save(new Notepad(path.directory(), path.filename(), content));
		return !existed;
	}

	public void delete(Path path) {
		repository.deleteById(path);
	}

	public List<String> list(String folder) {
		return repository
			.findByDirectory(folder)
			.stream()
			.map(Notepad::filename)
			.collect(Collectors.toList());
	}

}
