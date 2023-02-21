package baioc.dontpad;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface NotepadRepository extends JpaRepository<Notepad, Path> {

	Optional<Notepad> findByDirectoryAndFilename(String directory, String filename);
	List<Notepad> findByDirectory(String directory);

}
