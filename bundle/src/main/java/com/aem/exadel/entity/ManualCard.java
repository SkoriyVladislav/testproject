package com.aem.exadel.entity;

import com.adobe.cq.sightly.WCMUsePojo;
import lombok.Data;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Data
@Model(adaptables = Resource.class)
public class ManualCard extends WCMUsePojo {
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
