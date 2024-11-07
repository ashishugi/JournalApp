package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Value("${weather.api.key}")
    private String API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    //https://weatherstack.com/documentation
    public WeatherResponse getWeather(String city) {
        String finalApiEndPoint = "https://api.weatherstack.com/current?access_key=" + API_KEY + "&query=" + city;

        // here received output will be deserialize to WeatherResponse.class.
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApiEndPoint, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
/*
* In case we need to send a post call:
*      public WeatherResponse aPostCall(String city) {
        String finalApiEndPoint = "https://api.weatherstack.com/current?access_key=" + API_KEY + "&query=" + city;

        // SETTING HEADERS

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Key", "Value");

        // REQUEST BODY
        String requestBody = "Afdadfadf";
        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, httpHeaders);

        // POST CALL

        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApiEndPoint, HttpMethod.POST, httpEntity, WeatherResponse.class);
        return response.getBody();
    }
*
*
* */
}
