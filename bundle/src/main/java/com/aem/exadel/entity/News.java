package com.aem.exadel.entity;

import com.adobe.cq.sightly.WCMUsePojo;

public class News extends WCMUsePojo {
    private String title;
    private String link;
    private String description;
    private String enclosure;
    private String guid;
    private String pubDate;
    private String source;

    public News() {
    }

    public News(String title, String link, String description, String enclosure, String guid, String pubDate, String source) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.enclosure = enclosure;
        this.guid = guid;
        this.pubDate = pubDate;
        this.source = source;
    }

    @Override
    public void activate() throws Exception {

    }

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

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", enclosure='" + enclosure + '\'' +
                ", guid='" + guid + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
