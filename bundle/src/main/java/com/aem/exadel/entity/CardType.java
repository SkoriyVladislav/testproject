package com.aem.exadel.entity;

import lombok.Getter;

public enum CardType {
    MANUAL("TestProject/components/content/manual-Card", "manual"),
    DYNAMIC("TestProject/components/content/dynamic-Card", "dynamic");

    @Getter
    private String path;
    @Getter
    private String name;

    CardType(String path, String name) {
        this.path = path;
        this.name = name;
    }
}
