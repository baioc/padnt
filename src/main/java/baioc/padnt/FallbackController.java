package baioc.padnt;

import java.util.Random;

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

	private Random rand = new Random();

	@Value("${padnt.index.title}")
	private String title;

	@Value("${padnt.index.description}")
	private String[] descriptions;

	@Value("${padnt.index.placeholder}")
	private String[] placeholders;

	@Value("${padnt.index.github}")
	private String github;

	@GetMapping("/")
	public String getIndex(Model model) {
		var description = descriptions[rand.nextInt(descriptions.length)];
		var placeholder = placeholders[rand.nextInt(placeholders.length)];
		model.addAttribute("title", title);
		model.addAttribute("description", description);
		model.addAttribute("placeholder", placeholder);
		model.addAttribute("github", github);
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
