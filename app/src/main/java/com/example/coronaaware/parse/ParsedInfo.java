package com.example.coronaaware.parse;

import android.os.Handler;
import android.os.Message;

import com.example.coronaaware.News;
import com.example.coronaaware.info.Article;
import com.example.coronaaware.info.Statistics;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author DedUndead
 * @version 1.0
 */
public class ParsedInfo implements Runnable {
    private String url;
    private Handler myHand;
    private String tag;

    /**
     * Constructor with background handler and parsing target url
     * @param myHand handler to move data from the background thread
     * @param url website to be parsed
     * @param tag elements to be parsed
     */
    public ParsedInfo(Handler myHand, String url, String tag) {
        this.url = url;
        this.myHand = myHand;
        this.tag = tag;
    }

    /**
     * Parsing process running in the background thread
     */
    @Override
    public void run() {
        Message msg = myHand.obtainMessage();

        try {
            // Connect to the website and get the needed element
            Document doc = Jsoup.connect(this.url).get();
            Elements source = doc.getElementsByClass(tag);

            // Check what resource is being parced
            if (tag.equals("maincounter-number")) {
                msg.what = 0;
                msg.obj = parseStatistics(source);
            } else {
                parseArticle(source);
                msg.what = 1;
                msg.obj = "done";
            }

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
    private Statistics parseStatistics(Elements source) {
        Statistics statistics;

        String total = source.get(0).text();
        String death = source.get(1).text();
        String cured = source.get(2).text();

        statistics = new Statistics(total, death, cured);

        return statistics;
    }

    /**
     * Add the articles to list view
     * @param source parsed Elements
     */
    private void parseArticle(Elements source) {
        //ArrayList<Article> topics = new ArrayList<>();
        for (Element topic : source) {
            News.getInstance().getNewsList().add(new Article(topic.text()));
        }
    }
}