package com.example.coronaaware.info;

/**
 * @author andreyverbovskiy
 * @version 1.0
 */
public class Statistics {
    private String totalCases;
    private String death;
    private String cured;

    /**
     * Create Statistics with needed information
     * @param totalCases total infection cases
     * @param death total lethal cases
     * @param cured total cured cases
     */
    public Statistics(String totalCases, String death, String cured) {
        this.totalCases = totalCases;
        this.death = death;
        this.cured = cured;
    }

    /**
     *
     * @return all the infected cases
     */
    public String getTotalCases() {
        return this.totalCases;
    }

    /**
     *
     * @return all the lethal cases
     */
    public String getDeath() {
        return this.death;
    }

    /**
     *
     * @return all the cured cases
     */
    public String getCured() {
        return this.cured;
    }
}


