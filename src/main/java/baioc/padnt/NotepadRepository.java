package baioc.padnt;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface NotepadRepository extends Repository<Notepad, Path> {

	// Create/Update
	Notepad save(Notepad notepad);

	// Read
	boolean existsByDirectoryAndFilename(String directory, String filename);
	Optional<Notepad> findByDirectoryAndFilename(String directory, String filename);
	List<Notepad> findByDirectoryOrderByFilenameAsc(String directory);

	// Delete
	void deleteByDirectoryAndFilename(String directory, String filename);

}
