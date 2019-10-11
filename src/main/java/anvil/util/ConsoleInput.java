package anvil.util;

import anvil.config.CreateSearchIndexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleInput {

    @Autowired
    CreateSearchIndexer createSearchIndexer;

    @Async
    public void awaitUserInput() {

        try (Scanner scanner = new Scanner(System.in)) {

            while(true) {

                String input = scanner.nextLine();
                processUserInput(input);
            }

        } catch(Exception ex) {

            System.out.println("User input loop terminated due to caught exception...");
        }
    }

    public void processUserInput(String input) throws InterruptedException {

        switch(input) {

            case "createSearchIndexer": case "csi":
                System.out.println("Will attempt to call createSearchIndexer");
                createSearchIndexer.createSearchIndexer();
                break;
            default:
                break;
        }
    }
}
