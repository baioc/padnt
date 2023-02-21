package baioc.dontpad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface NotepadRepository extends JpaRepository<Notepad, Notepad.Path> {

	Notepad findByDirectoryAndFilename(String directory, String filename);
	List<Notepad> findByDirectory(String directory);

}
