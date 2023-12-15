import {JsonRequest} from "./json_request.js";
import {getElement} from "./helperMethod.js";
import {checkCookie} from "./anonymous_user_cookie.js";


class CookieManager {
    static cookieBasket = localStorage;

    constructor() {
    }

    static getCookieHeader() {
        let basket = new Set();
        for (let i = 0; i < this.cookieBasket.length; i++) {
            basket.add(this.cookieBasket.key(i));
        }
        return basket;
    }



    static async addCookie(cookie) {
        try {
            // console.log(quantity);
            const userId = "user12345";
            //const artwork = ElementManager.createArtWorkObject(cookieValue, cookie.type);


            const response = await new JsonRequest().post(`/${cookie.type}/burger/save`, {
                burgerId: cookie.id,
                customerId: checkCookie(userId),


            });

            if (!response.ok) {
                throw new Error("Sorry your artwork could not be save");

            }
            getElement(`.${cookie.type}-count`).innerHTML = Number(getElement(`.${cookie.type}-count`).innerHTML) + 1;

            console.log("artwork save successfully");

            console.log(response);

            return response;

        } catch (error) {
            console.log(error);
            alert(error)
        }

    }

    static async removeCookie(cookie) {

        try {
            const artworkId = cookie.id; // ID of the resource to be deleted
            const userId = checkCookie("user12345");
            const response = await fetch(`/${cookie.type}/burger/${userId}/${artworkId}`, {
                method: 'DELETE'
            });


            getElement(`.${cookie.type}-count`).innerHTML = Number(getElement(`.${cookie.type}-count`).innerHTML) - 1;

            return response;
        } catch (error) {

            console.log('Sorry an unexpected error occurred.' +
                'Failed to delete resource!' + error);
            alert('Sorry an unexpected error occurred.' +
                'Failed to delete resource!' + error);
            // handle error
        }

    }

}

class Cookie {
    constructor(type, id) {
        this.cookieType = type;
        this.cookieID = id;
    }

    get id() {
        return this.cookieID;
    }

    get type() {
        return this.cookieType;
    }
}

class CookieClient {
    constructor() {
        this.allCookies = CookieManager.getCookieHeader();
        this.clientBasket = new Set();
    }




   static displayNumberLike(numAdd, type) {
        fetch(`/${type}/burger/${checkCookie("user12345")}`).then(response => {
            if (!response.ok) {
                throw new Error("Buyer cart is empty");
            } else {
                return response.json()
            }
        }).then(data => {
            let sumLike = data.length;
            if (sumLike >= 1) {
                document.querySelector(numAdd).innerHTML = sumLike;
            }

        }).catch((er) => {
            return er;
        });
    }




    async displayArtworkDataFromDB(parent_element, common_op, type) {
        try {
            const response = await fetch(`/${type}/burger/${checkCookie("user12345")}`);
            const data = await response.json();


            data.forEach(
                value => {

                    const clone = document.querySelector(".card-template");
                    const cloneNode = clone.content.cloneNode(true);
                    cloneNode.querySelector(".burger-id").src = value.id;

                    cloneNode.querySelector(".card-img-top").src = value.imageUrl;
                    cloneNode.querySelector(".title").innerHTML = value.title
                    cloneNode.querySelector(".price").innerHTML = value.price;

                    const common = cloneNode.querySelectorAll(common_op);

                    for (let y = 0; y < common.length; y++) {
                        common[y].innerHTML = value.ingredients[y];
                    }


                    document.querySelector(parent_element).appendChild(cloneNode);

                });


            return data;

        } catch (e) {
            getElement(".cart-empty").style.display = "block";
            getElement(".menu-parent-div").style.alignItems="center";
            getElement(".menu-parent-div").style.justifyContent="center";
            getElement(".menu-parent-div").style.height="100vh";


            return "Your is Empty"

        }


    }


}

class ElementManager {
    constructor(element1, element2) {
        this.quantityValue = 1;
        this.element1 = element1;

        this.element2 = element2;

    }

    checkVisibilityOrDisplay(operation, element) {
        operation === "display" ? element.style.display = "flex" : element.style.visibility = "visible";
    }


    async displayElementStatusFromDB( operation, cookie) {
        try {
            const response = await fetch(`/${cookie.type}/burger/${checkCookie("user12345")}/${cookie.id}`);

            const data = await response.json();
            this.checkVisibilityOrDisplay(operation, this.element1);
            return data;
        } catch (er) {

            this.checkVisibilityOrDisplay(operation, this.element2);
            return er;
        }


    }


    showElement(cookie, operation) {


        try {
            CookieManager.removeCookie(cookie).then(response => {
                if (response.ok) {
                    if (operation === "display") {
                        this.element1.style.display = "none";
                        this.element2.style.display = "flex";

                    } else if (operation === "visibility") {
                        this.element1.style.visibility = "hidden";
                        this.element2.style.visibility = "visible";

                    }
                }
            });
        } catch (err) {
            alert("Sorry an internal error occurs, Please try again " + err);
        }

    }

    notShowElement(cookie, operation) {

        try {
            CookieManager.addCookie(cookie, this.quantityValue).then(response => {
                if (response.ok) {
                    if (operation === "display") {
                        this.element1.style.display = "flex";
                        this.element2.style.display = "none";

                    } else if (operation === "visibility") {
                        this.element1.style.visibility = "visible";
                        this.element2.style.visibility = "hidden";

                    }


                }
            });
        } catch (err) {
            alert("Sorry an internal error occurs, Please try again " + err);
        }

    }


    switchAppearance(cookie, operation) {
        this.displayElementStatusFromDB(operation, cookie).then(data => data);


        this.element1.onclick = () => {
            this.showElement(cookie, operation);
        }
        this.element2.onclick = () => {
            this.notShowElement( cookie, operation);

        }

    }


}

export {ElementManager, Cookie, CookieManager, CookieClient};