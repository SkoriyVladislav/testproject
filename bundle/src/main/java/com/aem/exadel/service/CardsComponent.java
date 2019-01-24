package com.aem.exadel.service;

import com.adobe.cq.sightly.WCMUsePojo;
import com.aem.exadel.entity.Card;
import com.aem.exadel.entity.CardType;
import lombok.Getter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import java.util.ArrayList;
import java.util.List;

public class CardsComponent extends WCMUsePojo {
    @Getter
    private List<Card> list = new ArrayList<>();

    @Override
    public void activate() throws Exception {
        Iterable<Resource> iterable = getResource().getChildren();
        for(Resource resource : iterable) {
            Iterable<Resource> cards = resource.getChildren();
            for (Resource card : cards) {
                ValueMap valueMap = card.getValueMap();

                String url = valueMap.get("./jcr:url").toString();
                String type = valueMap.get("./sling:resourceType").toString();

                if (type.equals(CardType.DYNAMIC.getPath())) {
                    list.add(DynamicCardRestComponent.createCard(url));
                } else if (type.equals(CardType.MANUAL.getPath())) {
                    list.add(ManualCardRestComponent.createCard(getRequest(), url));
                }
            }
        }
    }

}
