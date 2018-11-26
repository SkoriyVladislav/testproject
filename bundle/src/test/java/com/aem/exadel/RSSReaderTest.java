package com.aem.exadel;

import com.aem.exadel.entity.News;
import com.aem.exadel.service.RSSReader;
import com.aem.exadel.service.impl.RSSReaderImpl;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class RSSReaderTest {
    @Test
    public void readTest(){
        RSSReader rssReader = new RSSReaderImpl("https://www.nasa.gov/rss/dyn/breaking_news.rss");
        List<News> newsList = rssReader.readFeed();
        assertEquals(10, newsList.size());
        for (News news : newsList) {
            System.out.println(news.getTitle());
        }
    }
}
