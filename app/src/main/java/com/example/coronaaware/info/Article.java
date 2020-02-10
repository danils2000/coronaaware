package com.example.coronaaware.info;

/**
 * @author danils2000
 * @version 1.0
 */
public class Article {
    // topic, back, constr with topic
    private String topic;

    /**
     * Create article with information
     * @param topic
     */
    public Article (String topic) {
        this.topic = topic;
    }

    /**
     *
     * @return topic
     */
    public String getTopic () {
        return this.topic;
    }
}


