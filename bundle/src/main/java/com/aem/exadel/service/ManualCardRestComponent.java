package com.aem.exadel.service;

import com.adobe.cq.sightly.WCMUsePojo;
import com.aem.exadel.entity.ManualCard;

import com.aem.exadel.entity.News;
import lombok.Getter;
import lombok.Setter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.jcr.Node;
import java.util.ArrayList;
import java.util.List;

public class ManualCardRestComponent extends WCMUsePojo {

    protected static final String RESOURCE_TYPE = "TestProject/components/content/manual-Card";
    private static final String LINK = "http://localhost:4502/|%s|?wcmmode=disabled";
    @Getter
    @Setter
    private ManualCard card;

    @Override
    public void activate() throws Exception {
        Node currentNode = getResource().adaptTo(Node.class);
        String link;

        if(currentNode.hasProperty("jcr:url")){
            link = currentNode.getProperty("./jcr:url").getString();
        } else {
            return;
        }
        ResourceResolver resourceResolver = getRequest().getResourceResolver();
        Resource resource = resourceResolver.getResource(link + "/jcr:content/content/article/content");

        //manualCard = resource.adaptTo(com.aem.exadel.entity.ManualCard.class); test
        card = getCard(resource);
    }

    private static ManualCard getCard(Resource resource) {
        if (resource != null) {
            News news = new News();
            ValueMap valueMap = resource.getValueMap();

            news.setTitle(valueMap.get("./jcr:title").toString());
            news.setDescription(valueMap.get("./jcr:description").toString());
            String str = valueMap.get("./jcr:url").toString();
            news.setLink(String.format(LINK,valueMap.get("./jcr:url").toString()));
            news.setPubDate(valueMap.get("./jcr:pubDate").toString());

            ManualCard manualCard = new ManualCard();
            List<News> arr = new ArrayList<>();
            arr.add(news);
            manualCard.setNews(arr);
            return manualCard;
        }
        return null;
    }

}
