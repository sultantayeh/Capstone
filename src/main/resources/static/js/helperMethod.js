import {Cookie, ElementManager} from "./DataManager.js";

export function getAllElement(selector) {
    return document.querySelectorAll(selector);
}

export function getElement(selector) {
    return document.querySelector(selector);
}
export function cartAddRemoveDesign(cookieNum, cart_remove, cart_add) {
    const cookieForCart = new Cookie("cart", cookieNum);

    const elementManagerCart = new ElementManager(cart_remove, cart_add);

elementManagerCart.switchAppearance( cookieForCart, "display" );

}

