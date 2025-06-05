package pl.gastro.gastro_management_suite.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.gastro.gastro_management_suite.model.Address;

@Service
@RequiredArgsConstructor
public class LocationIqService {

    @Value("${locationiq.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public void fillCoordinates(Address address) {
        String query = String.format("%s, %s, %s, %s",
                address.getStreet(),
                address.getCity(),
                address.getPostalCode(),
                address.getCountry());

        String url = String.format("https://us1.locationiq.com/v1/search.php?key=%s&q=%s&format=json",
                apiKey, query.replace(" ", "+"));

        try {
            String json = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            if (root.isArray() && root.size() > 0) {
                JsonNode result = root.get(0);
                address.setLatitude(Double.parseDouble(result.get("lat").asText()));
                address.setLongitude(Double.parseDouble(result.get("lon").asText()));
            }
        } catch (Exception e) {
            System.err.println("Błąd podczas pobierania współrzędnych z LocationIQ: " + e.getMessage());
        }
    }
}
