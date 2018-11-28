/*
package com.aem.exadel.service.impl;

import com.adobe.cq.sightly.WCMUsePojo;
import com.aem.exadel.entity.Card;
import com.aem.exadel.service.CardsRestComponent;
import org.apache.sling.api.resource.Resource;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


public class CardsRestComponentImpl extends WCMUsePojo implements CardsRestComponent {
    private List<Card> cards;
    @Inject
    Resource resource;
    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public void activate() throws Exception {
        cards = new ArrayList<>();
        Iterable<Resource> resourceList = resource.getChildren();
        for (Resource res : resourceList) {
            org.apache.sling.api.resource.ValueMap valueMap = res.getValueMap();
            valueMap.size();
        }
    }
}
*/
