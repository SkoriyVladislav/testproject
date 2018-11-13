package com.aem.exadel.service;

import com.adobe.cq.sightly.WCMUse;
import com.aem.exadel.entity.DynamicCard;
import com.aem.exadel.service.impl.RSSReaderImpl;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import java.util.List;

public class DynamicCardRestComponent extends WCMUse {
    @Getter
    @Setter
    private List<DynamicCard> news;
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void activate() throws Exception {
        Node currentNode = getResource().adaptTo(Node.class);
        String link;

        if(currentNode.hasProperty("jcr:url")){
            link = currentNode.getProperty("./jcr:url").getString();
        } else {
            return;
        }

        RSSReader rssReader = new RSSReaderImpl(link);
        news = rssReader.readFeed();

    }
}
