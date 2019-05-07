package anvil.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Note: the Main Controller is configured to return index for all
 * valid urls. Routing is then handled client-side by React Router.
 */

@Controller
@RequestMapping({"/", "artistSearch", "artistInfo", "register", "login"})
public class MainController {

	@GetMapping
	public String index() {
		return "index.html";
	}
}
