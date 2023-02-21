package baioc.dontpad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class WebController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final NotepadService notepads;

	@Autowired
	public WebController(NotepadService service) {
		this.notepads = service;
	}

	@GetMapping("/**")
	public String get(HttpServletRequest req, Model model) {
		var path = parseRequestPath(req);
		String content = notepads.read(path);
		model.addAttribute("filename", path.filename());
		model.addAttribute("content", content);
		return "notepad";
	}

	@PutMapping("/**")
	public String put(HttpServletRequest req, @RequestBody(required = false) String body, Model model) {
		if (body == null) return delete(req, model);
		var path = parseRequestPath(req);
		notepads.upsert(path, body);
		model.addAttribute("filename", path.filename());
		model.addAttribute("content", body);
		return "notepad";
	}

	@DeleteMapping("/**")
	public String delete(HttpServletRequest req, Model model) {
		var path = parseRequestPath(req);
		notepads.delete(path);
		model.addAttribute("filename", path.filename());
		model.addAttribute("content", "");
		return "notepad";
	}

	private Path parseRequestPath(HttpServletRequest req) {
		// validate URL path format
		String rawpath = req.getRequestURI();
		if (!rawpath.matches("(/[A-Za-z0-9_\\-]+)*/?")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		// '/api' endpoint prefix needs trimming
		assert rawpath.startsWith("/api/");
		String fullpath = rawpath.substring(4);

		// trim trailing slash, if any
		String trimmed =
			fullpath.lastIndexOf('/') == fullpath.length() - 1
				? fullpath.substring(0, fullpath.length() - 1)
				: fullpath;

		// split into <dir_path>/<file_name>
		int separatorPosition = trimmed.lastIndexOf('/');
		String directory = trimmed.substring(0, separatorPosition + 1);
		String filename = trimmed.substring(separatorPosition + 1);

		// make path record
		logger.debug("'" + trimmed + "' = '" + directory + "' + '" + filename + "'");
		var path = new Path(directory, filename);
		return path;
	}

}
