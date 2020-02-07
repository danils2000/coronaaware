package com.example.coronaaware.info;

import android.os.Handler;
import android.os.Message;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author dedundead
 * @version 1.0
 */
public class ParsedInfo implements Runnable {
    private String url;
    private Handler myHand;

    /**
     * Constructor with backgorund handler and parsing target url
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

            msg.what = 0;
            msg.obj = parseStatistics(doc.title());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception pokemon) {
            pokemon.printStackTrace();
        }

        myHand.sendMessage(msg);

    }

    public String parseStatistics(String source) {
        return "";
    }
}
