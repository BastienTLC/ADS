package se.umu.cs.ads.a1.rest.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionRequest {
    private Username username;
    private Topic topic;

    @JsonCreator
    public SubscriptionRequest(@JsonProperty("username") Username username, @JsonProperty("topic") Topic topic) {
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
