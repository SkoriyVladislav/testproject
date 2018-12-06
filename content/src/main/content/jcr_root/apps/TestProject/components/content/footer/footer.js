"use strict";
use(function () {
    var CONST = {
        PROP_FOOTER_TEXT: "jcr:footer-text",
        PROP_URL: "jcr:footer-url-feedbook",
        PROP_FEEDBOOK_TEXT : "jcr:footer-text-feedbook"
    };
    var footer = {};
    footer.footer_text = granite.resource.properties[CONST.PROP_FOOTER_TEXT] || "Footer text";
    footer.url = granite.resource.properties[CONST.PROP_URL] || "Footer url feedbook";
    footer.footer_feedbook = granite.resource.properties[CONST.PROP_FEEDBOOK_TEXT] || "Footer text feedbook";
    return footer;
});