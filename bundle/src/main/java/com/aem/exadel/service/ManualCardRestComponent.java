package com.aem.exadel.service;

import com.adobe.cq.sightly.WCMUsePojo;
import com.aem.exadel.entity.ManualCard;

import lombok.Getter;
import lombok.Setter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;

public class ManualCardRestComponent extends WCMUsePojo {

    @Getter
    @Setter
    private ManualCard manualCard = null;
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

        ResourceResolver resourceResolver = getRequest().getResourceResolver();
        Resource resource = resourceResolver.getResource(link + "/jcr:content/content/article");
        ValueMap valueMap = resource.getValueMap();

        manualCard = getCard(valueMap);
    }


    private static ManualCard getCard(ValueMap valueMap) {
        ManualCard manualCard = new ManualCard();
        manualCard.setTitle(valueMap.get("./jcr:title").toString());
        manualCard.setDescription(valueMap.get("./jcr:title").toString());
        manualCard.setLink("link");
        manualCard.setPubDate(valueMap.get("./jcr:pubDate").toString());

        return manualCard;
    }

}
