package com.aem.exadel.entity;

import com.adobe.cq.sightly.WCMUsePojo;

public class ManualCard extends WCMUsePojo {
    String title;
    String link;
    String description;
    String pubDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public void activate() throws Exception {
        this.title = get("title", String.class);
        this.link = get("link", String.class);
        this.pubDate = get("pubDate", String.class);
        this.description = get("description", String.class);
    }
}
