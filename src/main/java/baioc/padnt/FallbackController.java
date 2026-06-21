package baioc.padnt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.RequestDispatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FallbackController implements ErrorController {

	@Value("${padnt.index.title}")
	private String title;

	@Value("${padnt.index.description}")
	private String description;

	@Value("${padnt.index.github}")
	private String github;

	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("title", this.title);
		model.addAttribute("description", this.description);
		model.addAttribute("github", this.github);
		return "index";
	}

	@RequestMapping("/.error")
	public String handleError(HttpServletRequest req, Model model) {
		Object statusObject = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		int statusCode = statusObject != null ? (Integer) statusObject : 500;
		model.addAttribute("title", "Error: " + statusCode);
		model.addAttribute("message", "Invalid URL");
		return "error";
	}

}
