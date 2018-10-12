package com.aem.exadel.service;

import com.aem.exadel.entity.News;

import java.util.List;

public interface RSSReader {
    List<News> readFeed();
}
