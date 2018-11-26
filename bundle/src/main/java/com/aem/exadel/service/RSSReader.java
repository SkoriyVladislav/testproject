package com.aem.exadel.service;

import com.aem.exadel.entity.DynamicCard;

import java.util.List;

public interface RSSReader {
    List<DynamicCard> readFeed();
}
