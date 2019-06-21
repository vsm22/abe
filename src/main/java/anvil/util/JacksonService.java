package anvil.util;

import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;

@Service
public class JacksonService {

    @Bean
    @Qualifier("xmlMapper")
    public XmlMapper xmlMapper() {

//        XMLInputFactory input = new WstxInputFactory();
//        input.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
//        XmlMapper xmlMapper = new XmlMapper(new XmlFactory(input, new WstxOutputFactory()));

        return new XmlMapper();
    }

    @Bean
    @Qualifier("jsonMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
