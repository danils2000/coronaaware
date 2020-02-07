package com.example.coronaaware.parse;

import android.os.Handler;
import android.os.Message;

import com.example.coronaaware.info.Statistics;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author DedUndead
 * @version 1.0
 */
public class ParsedInfo implements Runnable {
    private String url;
    private Handler myHand;

    /**
     * Constructor with background handler and parsing target url
     * @param myHand handler to move data from the background thread
     * @param url website to be parsed
     */
    public ParsedInfo(Handler myHand, String url) {
        this.url = url;
        this.myHand = myHand;
    }

    /**
     * Parsing process running in the background thread
     */
    @Override
    public void run() {
        Message msg = myHand.obtainMessage();

        try {
            Document doc = Jsoup.connect(this.url).get();
            Elements sourceStats = doc.getElementsByClass("maincounter-number");
            // TODO: Find topics from news website, if logic for parsing

            msg.what = 0;
            msg.obj = parseStatistics(sourceStats);
        } catch (IOException e) {
            e.printStackTrace();
        }

        myHand.sendMessage(msg);

    }

    /**
     * Create the Statistics object from the parsed information
     * @param source parsed Elements
     * @return new Statistics object
     */
    public Statistics parseStatistics(Elements source) {
        Statistics statistics;

        String total = source.get(0).text();
        String death = source.get(1).text();
        String cured = source.get(2).text();

        statistics = new Statistics(total, death, cured);

        return statistics;
    }

    //TODO Method for parsing topics
}
