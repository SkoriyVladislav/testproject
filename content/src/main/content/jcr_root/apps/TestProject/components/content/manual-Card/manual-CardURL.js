"use strict";
use(function () {
    var CONST = {
    	PROP_URL: "linkURL",
	}
	var linkURL = {};
	linkURL.text = granite.resource.properties[CONST.PROP_URL];
	return linkURL;
});