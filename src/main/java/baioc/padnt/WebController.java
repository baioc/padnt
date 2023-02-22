package baioc.padnt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@GetMapping(value="/**", produces="text/html")
	public String getAsHtml(HttpServletRequest req, Model model) {
		// fetch notepad
		var path = parseRequestPath(req);
		String content = notepads.read(path);
		// make view
		model.addAttribute("directory", path.directory());
		model.addAttribute("filename", path.filename());
		model.addAttribute("content", content);
		String folder = path.toString() + "/";
		model.addAttribute("folder", folder);
		model.addAttribute("subfiles", notepads.list(folder));
		return "notepad";
	}

	@GetMapping(value="/**", produces="text/plain")
	@ResponseBody
	public String getAsPlainText(HttpServletRequest req) {
		var path = parseRequestPath(req);
		return notepads.read(path);
	}

	@PutMapping(value="/**", consumes="text/plain")
	@ResponseBody
	public void put(HttpServletRequest req, @RequestBody(required = false) String body) {
		var path = parseRequestPath(req);
		if (body == null) {
			delete(req);
		} else {
			notepads.upsert(path, body);
		}
	}

	@DeleteMapping("/**")
	@ResponseBody
	public void delete(HttpServletRequest req) {
		var path = parseRequestPath(req);
		notepads.delete(path);
	}

	private Path parseRequestPath(HttpServletRequest req) {
		// validate URL path format and trim leading '/api'
		String rawpath = req.getRequestURI();
		if (!rawpath.matches("/api/([A-Za-z0-9_\\-]+/?)+")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
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
		logger.debug("'" + fullpath + "' = '" + directory + "' + '" + filename + "'");
		var path = new Path(directory, filename);
		return path;
	}

}
