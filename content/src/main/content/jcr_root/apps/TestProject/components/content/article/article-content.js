"use strict";
use(function () {
    var CONST = {
    	PROP_TITLE: "jcr:title",
    	PROP_CONTENT: "jcr:content",
    	PROP_PUB_DATE: "jcr:pubDate",
	};
	var article = {};

	article.title = granite.resource.properties[CONST.PROP_TITLE] || "Articles title";
	article.content = granite.resource.properties[CONST.PROP_CONTENT] || "Articles content";
	article.pubDate = granite.resource.properties[CONST.PROP_PUB_DATE] || "Articles publication date";

	return article;
});