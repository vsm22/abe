package anvil.config;

import anvil.util.ConsoleInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PostConstructInit {

    @Autowired
    ConsoleInput consoleInput;

    @PostConstruct
    public void init() {

        consoleInput.awaitUserInput();
    }
}
