package anvil.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
