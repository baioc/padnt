package baioc.dontpad;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
public class Controller {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final NotepadRepository repository;

	Controller(NotepadRepository repository) {
		this.repository = repository;
	}

	@GetMapping({"/", "/index.html"})
	public String index() {
		return "Hello, Spring World!";
	}

	@GetMapping("/api/**")
	String read(HttpServletRequest request) {
		String fullpath = request.getRequestURI();
		logger.info("fullpath = " + fullpath);
		int separatorPosition = fullpath.lastIndexOf('/');
		String directory = fullpath.substring(0, separatorPosition + 1);
		String filename = fullpath.substring(separatorPosition + 1);
		logger.info("directory = " + directory);
		logger.info("filename = " + filename);
		return fullpath + " = " + directory + " . " + filename;
		// return repository.findByDirectoryAndFilename(directory, filename).content();
	}

	@GetMapping("/log")
	public String testLog() {
		logger.error("this is a error-level log message");
		logger.warn("this is a warn-level log message");
		logger.info("this is a info-level log message");
		logger.debug("this is a debug-level log message");
		logger.trace("this is a trace-level log message");
		return "something was logged";
	}

}
