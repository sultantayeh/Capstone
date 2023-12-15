import {CookieClient} from "./DataManager.js";

class Header extends HTMLElement {
    constructor() {
        super();

    }


    connectedCallback() {


        this.innerHTML = `<head>
  <!-- Bootstrap CSS -->
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" defer></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />   <title></title>

    <body>

<nav class="d-flex navbar navbar-expand-sm bg-dark px-4 navbar-dark sticky-top">
  <div class="d-flex container-fluid">
     <li class="navbar-brand"> 
     <a href="#">
      <img class="img-fluid w-10 rounded-circle" style="max-width: 2rem" src="/image/logo.png" alt="logo"/>
</a> 
      </li>
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="/menu">Menu</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">About</a>
      </li>
      <li class="nav-item">
        <a class="nav-link fa fa-cart-arrow-down" href="/cart"><i style="color: red;" class="cart-count"></i></a>
      </li>
      
      
    </ul> 
     <li style="color: red" class="nav-item">
        <a class="nav-link" href="/">logout</a>
      </li>
  </div>
</nav>
        
    </body>`;
    }
}

customElements.define('header-component', Header);

CookieClient.displayNumberLike(".cart-count", "cart");




