package com.example.coronaaware.info;

import android.os.Message;

import android.os.Handler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ParsedInfo implements Runnable {
    private String url;
    private Handler myHand;

    public ParsedInfo(Handler myHand, String url) {
        this.url = url;
        this.myHand = myHand;
    }

    @Override
    public void run() {
        Message msg = myHand.obtainMessage();

        try {
            Document doc = Jsoup.connect(this.url).get();

            msg.what = 0;
            msg.obj= doc.title();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception pokemon) {
            pokemon.printStackTrace();
        }

        myHand.sendMessage(msg);

    }
}
