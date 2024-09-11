package se.umu.cs.ads.a1.backend;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.core.ParameterizedTypeReference;
import se.umu.cs.ads.a1.interfaces.Messenger;
import se.umu.cs.ads.a1.types.*;

import java.util.Arrays;
import java.util.List;

public class RestMessengerBackend implements Messenger {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8080/api/messenger";

    public RestMessengerBackend() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void store(Message message) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Message> request = new HttpEntity<>(message, headers);
            restTemplate.postForEntity(baseUrl, request, Void.class);
            //System.out.println("Message successfully stored.");
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
        }
    }

    @Override
    public void store(Message[] messages) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Message[]> request = new HttpEntity<>(messages, headers);
            restTemplate.postForEntity(baseUrl + "/batch", request, Void.class);
            //System.out.println("Batch of messages successfully stored: " + messages.length + " messages");
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
        }
    }

    @Override
    public Message retrieve(MessageId messageId) {
        try {
            ResponseEntity<Message> response = restTemplate.getForEntity(baseUrl + "/" + messageId, Message.class);
            return (Message) response.getBody();
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Message[] retrieve(MessageId[] messageIds) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<MessageId[]> request = new HttpEntity<>(messageIds, headers);
            ResponseEntity<Message[]> response = restTemplate.exchange(
                    baseUrl + "/batch/retrieve",
                    HttpMethod.POST,
                    request,
                    Message[].class
            );
            return response.getBody();
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(MessageId messageId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<MessageId> request = new HttpEntity<>(messageId, headers);
            restTemplate.exchange(
                    baseUrl + "/delete",
                    HttpMethod.DELETE,
                    request,
                    Void.class
            );
            //System.out.println("Message successfully deleted.");
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
        }
    }

    @Override
    public void delete(MessageId[] messageIds) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<MessageId[]> request = new HttpEntity<>(messageIds, headers);
            restTemplate.exchange(
                    baseUrl + "/batch/delete",
                    HttpMethod.DELETE,
                    request,
                    Void.class
            );
            //System.out.println("Batch of messages successfully deleted: " + messageIds.length + " messages");
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
        }
    }

    @Override
    public Topic[] subscribe(Username username, Topic topic) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            SubscriptionRequest subscriptionRequest = new SubscriptionRequest(username, topic);
            HttpEntity<SubscriptionRequest> request = new HttpEntity<>(subscriptionRequest, headers);
            ResponseEntity<Topic[]> response = restTemplate.postForEntity(baseUrl + "/subscribe", request, Topic[].class);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Topic[] unsubscribe(Username username, Topic topic) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<SubscriptionRequest> request = new HttpEntity<>(new SubscriptionRequest(username, topic), headers);
            ResponseEntity<Topic[]> response = restTemplate.postForEntity(baseUrl + "/unsubscribe", request, Topic[].class);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Username[] listUsers() {
        try {
            ResponseEntity<Username[]> response = restTemplate.getForEntity(baseUrl + "/users", Username[].class);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Topic[] listTopics() {
        try {
            ResponseEntity<Topic[]> response = restTemplate.getForEntity(baseUrl + "/topics", Topic[].class);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Topic[] listTopics(Username username) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Username> request = new HttpEntity<>(username, headers);
            ResponseEntity<Topic[]> response = restTemplate.exchange(
                    baseUrl + "/topics/" + username.getValue(),
                    HttpMethod.POST,
                    request,
                    Topic[].class
            );
            return response.getBody();
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Username[] listSubscribers(Topic topic) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Topic> request = new HttpEntity<>(topic, headers);
            ResponseEntity<Username[]> response = restTemplate.exchange(
                    baseUrl + "/subscribers/" + topic.getValue(),
                    HttpMethod.GET,
                    request,
                    Username[].class
            );
            return response.getBody();
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
            return null;
        }
    }

    @Override
    public MessageId[] listMessages(Username username) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Username> request = new HttpEntity<>(username, headers);
            ResponseEntity<MessageId[]> response = restTemplate.exchange(
                    baseUrl + "/message/user/" + username.getValue(),
                    HttpMethod.POST,
                    request,
                    MessageId[].class
            );
            return response.getBody();
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
            return null;
        }
    }

    @Override
    public MessageId[] listMessages(Topic topic) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Topic> request = new HttpEntity<>(topic, headers);
            ResponseEntity<MessageId[]> response = restTemplate.exchange(
                    baseUrl + "/message/topic/",
                    HttpMethod.POST,
                    request,
                    MessageId[].class
            );
            return response.getBody();
        } catch (Exception e) {
            System.err.println("REST call failed: " + e.getMessage());
            return null;
        }
    }

    private static class SubscriptionRequest {
        private Username username;
        private Topic topic;

        public SubscriptionRequest(Username username, Topic topic) {
            this.username = username;
            this.topic = topic;
        }

        @JsonProperty("username")
        public Username getUsername() {
            return username;
        }

        @JsonProperty("topic")
        public Topic getTopic() {
            return topic;
        }
    }
}
