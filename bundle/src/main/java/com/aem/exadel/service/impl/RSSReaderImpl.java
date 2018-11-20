package com.aem.exadel.service.impl;

import com.aem.exadel.entity.DynamicCard;
import com.aem.exadel.service.RSSReader;
import lombok.Getter;
import lombok.Setter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class)
public class RSSReaderImpl implements RSSReader {
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LINK = "link";
    private static final String ITEM = "item";
    private static final String PUB_DATE = "pubDate";
    private static final String GUID = "guid";
    private static final String ENCLOSURE = "enclosure";
    private static final String SOURCE = "source";

    @Getter
    @Setter
    private URL url;
    @Getter
    @Setter
    private List<DynamicCard> dynamicCards;

    public RSSReaderImpl() {
        try {
            this.url = new URL("https://www.nasa.gov/rss/dyn/breaking_news.rss");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public RSSReaderImpl(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DynamicCard> readFeed() {
        ArrayList<DynamicCard> feed = null;
        try {
            boolean isFeedHeader = true;
            String description = "";
            String title = "";
            String link = "";
            String pub_date = "";
            String guid = "";
            String enclosure = "";
            String source = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName().getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                feed = new ArrayList<>();
                            }
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;
                        case GUID:
                            guid = getCharacterData(event, eventReader);
                            break;
                        case PUB_DATE:
                            pub_date = getCharacterData(event, eventReader);
                            break;
                        case ENCLOSURE:
                            enclosure = getCharacterData(event, eventReader);
                            break;
                        case SOURCE:
                            source = getCharacterData(event, eventReader);
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        DynamicCard dynamicCard = new DynamicCard();
                        dynamicCard.setTitle(title);
                        dynamicCard.setDescription(description);
                        dynamicCard.setGuid(guid);
                        dynamicCard.setLink(link);
                        dynamicCard.setPubDate(pub_date);
                        dynamicCard.setEnclosure(enclosure);
                        dynamicCard.setSource(source);
                        feed.add(dynamicCard);
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        this.dynamicCards = feed;
        return feed;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
