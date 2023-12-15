import {Cookie, CookieManager, ElementManager} from "./DataManager.js";
import {cartAddRemoveDesign, getAllElement} from "./helperMethod.js";
const allId = getAllElement(".burger-id");
const allAddButton = getAllElement(".add");
const allRemoveButton = getAllElement(".remove");

for (let i = 0; i < allId.length; i++) {

    (() => {
        cartAddRemoveDesign(allId[i].value, allRemoveButton[i], allAddButton[i]);
    })();
}

