package baioc.padnt;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NotepadService {

	private final NotepadRepository repository;

	NotepadService(NotepadRepository repository) {
		this.repository = repository;
	}

	public String read(Path path) {
		return repository
			.findById(path)
			.orElse(new Notepad(path, ""))
			.content();
	}

	public void upsert(Path path, String content) {
		var notepad = new Notepad(path, content);
		repository.save(notepad);
	}

	public void delete(Path path) {
		repository.deleteByDirectoryAndFilename(path.directory(), path.filename());
	}

	public List<String> list(String folder) {
		return repository
			.findByDirectoryOrderByFilenameAsc(folder)
			.stream()
			.map(Notepad::filename)
			.toList();
	}

}
