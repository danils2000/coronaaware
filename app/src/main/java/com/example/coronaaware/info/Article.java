package com.example.coronaaware.info;

/**
 * @author danils2000
 * @version 1.0
 */
public class Article {
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
     * @return topic of the article
     */
    public String getTopic () {
        return this.topic;
    }
}


