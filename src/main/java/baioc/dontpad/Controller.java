package baioc.dontpad;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
public class Controller {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/")
	public String index() {
		return "Hello, Spring World!";
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
