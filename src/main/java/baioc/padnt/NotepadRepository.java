package baioc.padnt;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface NotepadRepository extends Repository<Notepad, Path> {

	// Create/Update
	Notepad save(Notepad notepad);

	// Read
	Optional<Notepad> findById(Path path);
	List<Notepad> findByDirectoryOrderByFilenameAsc(String directory);

	// Delete
	@Transactional // XXX: see https://stackoverflow.com/q/32269192
	void deleteByDirectoryAndFilename(String directory, String filename);

}
