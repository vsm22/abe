package anvil.web;

import anvil.security.entities.user.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Note: the Main Controller is configured to return index for all
 * valid urls. Routing is then handled client-side by React Router.
 */

@Controller
@RequestMapping({"/",
		"artistSearch",
		"artistInfo",
		"register",
		"login",
		"logout",
		"createArtistCollection",
		"getArtistCollections"})
public class IndexController {

	@GetMapping
	public String index() {
		return "index.html";
	}
}
