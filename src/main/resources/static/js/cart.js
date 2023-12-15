import {Cookie, CookieClient, CookieManager} from "./DataManager.js";
import {checkCookie} from "./anonymous_user_cookie.js";
import {getAllElement, getElement} from "./helperMethod.js";


console.log(document.cookie);
console.log(checkCookie("12345"));

new CookieClient().displayArtworkDataFromDB(".menu-parent-div", ".common-op", "cart").then(data => {
    for (let y = 0; y < getAllElement(".remove").length; y++) {
        getAllElement(".remove")[y].onclick = () => {
            CookieManager.removeCookie(new Cookie("cart", data[y].id)).then(data=>location.reload())
        }
    }

});



