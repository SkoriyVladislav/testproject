package com.aem.exadel.entity;

import com.adobe.cq.sightly.WCMUse;
import lombok.Data;

@Data
public class ManualCard extends WCMUse {
    private String title;
    private String link;
    private String description;
    private String pubDate;

    @Override
    public void activate() throws Exception {
        this.title = get("title", String.class);
        this.link = get("link", String.class);
        this.pubDate = get("pubDate", String.class);
        this.description = get("description", String.class);
    }
}
