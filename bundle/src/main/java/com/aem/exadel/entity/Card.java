package com.aem.exadel.entity;

import java.util.List;

public interface Card {
    CardType getCardType();
    List<News> getNews();
}
