package quantum.ai.namefake;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.client.RestTemplate;


public class NameFakeAPIService {

    private String url = "https://api.namefake.com/";
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper;

    public NameFakeAPIService() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SneakyThrows
    public NameFakeAPIResponse getFakePerson() {
        return mapper.readValue(restTemplate.getForObject(url, String.class), NameFakeAPIResponse.class) ;
    }
}
