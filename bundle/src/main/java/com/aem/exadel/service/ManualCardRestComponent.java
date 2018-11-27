package com.aem.exadel.service;

import com.adobe.cq.sightly.WCMUsePojo;
import com.aem.exadel.entity.ManualCard;

import lombok.Getter;
import lombok.Setter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import javax.jcr.Node;

public class ManualCardRestComponent extends WCMUsePojo {

    protected static final String RESOURCE_TYPE = "TestProject/components/content/manual-Card";

    @Getter
    @Setter
    private com.aem.exadel.entity.ManualCard manualCard = null;

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

        //manualCard = resource.adaptTo(com.aem.exadel.entity.ManualCard.class);
        manualCard = getCard(resource);
    }

    private static ManualCard getCard(Resource resource) {
        if (resource != null) {
            ManualCard manualCard = new ManualCard();
            ValueMap valueMap = resource.getValueMap();

            manualCard.setTitle(valueMap.get("./jcr:title").toString());
            manualCard.setDescription(valueMap.get("./jcr:description").toString());
            manualCard.setLink("link");
            manualCard.setPubDate(valueMap.get("./jcr:pubDate").toString());
            return manualCard;
        }
        return null;
    }

}
