"use strict";
use(function () {
    var CONST = {
        PROP_TITLE: "jcr:title",
        PROP_URL: "jcr:url",
    };
    var card = {};
    card.text = granite.resource.properties[CONST.PROP_TITLE] || "Manual card";
    card.url = granite.resource.properties[CONST.PROP_URL] || "Manual cards url";
    return card;
});