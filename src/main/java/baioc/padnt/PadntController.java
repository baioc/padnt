package baioc.padnt;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

// NOTE: class-level mapping matches any non-empty path without dots (includes slashes),
// so static resources MUST have a file extension. I couldn't get a more complex regex to
// work here, so actual path validation and separation is implemented in `parseRequestPath`.
@Controller
@RequestMapping("/{_:[^\\.]+$}")
public class PadntController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final NotepadService notepads;

	@Autowired
	public PadntController(NotepadService service) {
		notepads = service;
	}

	@GetMapping(path="/**", produces="text/html")
	public String getAsHtml(HttpServletRequest req, Model model) {
		// fetch notepad
		var path = parseRequestPath(req);
		String content = notepads.read(path);
		// make view
		model.addAttribute("directory", path.directory());
		model.addAttribute("filename", path.filename());
		model.addAttribute("content", content);
		String folder = path.directory() + path.filename() + "/";
		model.addAttribute("folder", folder);
		model.addAttribute("subfiles", notepads.list(folder));
		return "notepad";
	}

	@GetMapping(path="/**", produces="text/plain")
	@ResponseBody
	public String getAsPlainText(HttpServletRequest req) {
		var path = parseRequestPath(req);
		return notepads.read(path);
	}

	@PutMapping(path="/**", consumes="text/plain")
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
		// validate URL path format
		String fullpath = req.getRequestURI();
		if (!fullpath.matches("/([A-Za-z0-9_\\-]+/?)+")) {
			logger.error("invalid URL path '" + fullpath + "'");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		// trim trailing slash, if any
		String trimmed =
			fullpath.lastIndexOf('/') == fullpath.length() - 1
				? fullpath.substring(0, fullpath.length() - 1)
				: fullpath;

		// split into <dir_path>/<file_name>
		int separatorPosition = trimmed.lastIndexOf('/');
		String directory = trimmed.substring(0, separatorPosition + 1);
		String filename = trimmed.substring(separatorPosition + 1);
		// NOTE: slash is part of directory in this split ^

		// make path record
		logger.debug("'" + fullpath + "' = '" + directory + "' + '" + filename + "'");
		var path = new Path(directory, filename);
		return path;
	}

}
