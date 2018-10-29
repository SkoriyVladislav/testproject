package com.aem.exadel;

import com.aem.exadel.entity.DynamicCard;
import com.aem.exadel.service.RSSReader;
import com.aem.exadel.service.impl.RSSReaderImpl;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class RSSReaderTest {
    @Test
    public void readTest(){
        RSSReader rssReader = new RSSReaderImpl();
        List<DynamicCard> dynamicCardList = rssReader.readFeed();
        assertEquals(10, dynamicCardList.size());
        for (DynamicCard dynamicCard : dynamicCardList) {
            System.out.println(dynamicCard.getTitle());
        }
    }
}
