package com.example.coronaaware.info;

public class Statistics {
    private int totalCases;
    private int death;
    private int cured;

    public Statistics(int totalCases, int death, int cured) {
        this.totalCases = totalCases;
        this.death = death;
        this.cured = cured;
    }

    public int getTotalCases() {
        return this.totalCases;
    }
    public int death() {
        return this.death;
    }
    public int cured() {
        return this.cured;
    }
}


