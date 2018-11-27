package com.aem.exadel.entity;

import com.adobe.cq.sightly.WCMUsePojo;
import lombok.Data;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.osgi.service.component.annotations.Component;


@Model(adaptables = Resource.class)
@Data
public class DynamicCard extends WCMUsePojo {
    private String title;
    private String link;
    private String description;
    private String enclosure;
    private String guid;
    private String pubDate;
    private String source;

    public DynamicCard() {
    }

    public DynamicCard(String title, String link, String description, String enclosure, String guid, String pubDate, String source) {
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

    @Override
    public String toString() {
        return "DynamicCard{" +
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
