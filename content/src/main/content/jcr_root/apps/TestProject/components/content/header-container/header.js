"use strict";
use(function () {
    var CONST = {
    	PROP_LOGO: "jcr:logo",
	};
	var header = {};

	header.logo = granite.resource.properties[CONST.PROP_LOGO] || "LOGO";

	return header;
});