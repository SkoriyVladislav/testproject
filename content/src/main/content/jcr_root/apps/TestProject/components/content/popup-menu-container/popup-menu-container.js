"use strict";
use(function () {
    var CONST = {
    	PROP_MULTIFIELD: "lang",
        PROP_INPUT_VALUE: "Lang: $",
	};

    var langList = [];
    var langArray = properties.get(CONST.PROP_MULTIFIELD);
    var lang_menu = {};
    var default_lang = "" + langArray[0];

    if(langArray != null) {
        for(var i = 0; i < langArray.length; i++) {
        	var singleObj = {};
        	var itemObject = langArray[i];
        	singleObj.lang = itemObject;
            singleObj.id = i + 1;

        	langList.push(singleObj);
    	};

		lang_menu.linksList = langList;
    } else {
		lang_menu.linksList = ["UNDEF"];
    }

    lang_menu.inputValue = CONST.PROP_INPUT_VALUE;
    lang_menu.default_lang = default_lang;

    return lang_menu;

});