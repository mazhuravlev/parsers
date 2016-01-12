package com.company.models;

import java.net.URL;

public class Offer {

    public Offer(URL url) {
        originURL = url;
    }

    public long price;
    public String text;
    public URL originURL;
}
