package com.aem.exadel.service;

import com.adobe.cq.sightly.WCMUsePojo;
import com.aem.exadel.entity.DynamicCard;
import com.aem.exadel.entity.News;
import com.aem.exadel.service.RSSReader;
import com.aem.exadel.service.impl.RSSReaderImpl;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import java.util.List;

public class DynamicCardRestComponent extends WCMUsePojo {
    @Getter
    @Setter
    private DynamicCard card;
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

        card = createCard(link);
    }

    public static DynamicCard createCard(String link) {
        RSSReader rssReader = new RSSReaderImpl(link);

        DynamicCard card = new DynamicCard();
        List<News> news = rssReader.readFeed();
        card.setNews(news);
        return card;
    }
}
