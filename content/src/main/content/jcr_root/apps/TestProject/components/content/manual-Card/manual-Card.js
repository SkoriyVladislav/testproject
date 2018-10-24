"use strict";
use(function () {
    var CONST = {
    	PROP_TITLE: "jcr:title"
	};
	var card = {};
	card.text = granite.resource.properties[CONST.PROP_TITLE] || "Manual card";
	return card;
});