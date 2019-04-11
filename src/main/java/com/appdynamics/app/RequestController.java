package com.appdynamics.app;

import com.fasterxml.jackson.databind.JsonNode;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RequestController
{
    @RequestMapping("/sensor")
    public String response(@RequestBody JsonNode node) {
        final String baseURI = "http://localhost:8082/";
        String uri;
        String result;

        RestTemplate restTemplate = new RestTemplate();

        System.out.println("Handling request with temperature: "+ node.get("temp").asText() + ", humidity: " + node.get("humidity").asText() +".");

        uri = baseURI + "temperature/" + node.get("temp").asText();
        System.out.println("Sending GET to: " + uri);
        result = restTemplate.getForObject(uri, String.class);

        uri = baseURI + "humidity/" + node.get("humidity").asText();
        System.out.println("Sending GET to: " + uri);
        result = restTemplate.getForObject(uri, String.class);

        String response = "\"Response\":\"SUCCESS\"";
        System.out.println(response);
        return "\"Response\":\"SUCCESS\"";
    }
}