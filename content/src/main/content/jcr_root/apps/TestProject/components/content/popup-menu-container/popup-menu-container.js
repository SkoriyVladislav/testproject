"use strict";
use(function () {
    var CONST = {
    	PROP_MULTIFIELD: "lang",
        PROP_INPUT_VALUE: "Lang: $",
	};

    var langList = [];
    var langArray = properties.get(CONST.PROP_MULTIFIELD);
    var lang_menu = {};
    var default_lang;

    if(langArray != null) {
        for(var i = 0; i < langArray.length; i++) {
        	var singleObj = {};
        	var itemObject = langArray[i];
        	singleObj.lang = itemObject;
            singleObj.id = i + 1;

        	langList.push(singleObj);
    	};
    }

	lang_menu.linksList = langList;
    lang_menu.default_lang = lang_menu.linksList[0].lang;
    lang_menu.inputValue = CONST.PROP_INPUT_VALUE;
    return lang_menu;

});