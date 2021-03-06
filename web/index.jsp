<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="header.jsp"/>
    <body>
        <header>
            <!-- Logo forse da cambiare per evitare questa fastisiosa ripetizione dovuta alle specifiche 
                del progetto. Suggerirei un logo tipo il favicon che ho messo qualche settimana fa. -->
            <img src="img/favicon.png" alt="logo-fakeflix">
            <a href="index"><img title="fakeflix" alt="fakeflix" src="img/fakeFlix_logo.png" width="350" height="150"></a>
            <!-- <h1>FakeFlix</h1> -->
        </header>
       
        <nav class="col-12">
            <ul>
                <li class="active col-2"><a href="index">Home</a></li>
                <li class="col-2"><a href="about">About</a></li>
                <li class="col-2"><a href="catalogo">Catalogo</a></li>  
                <li class="col-2"><a href="nuovoProdotto">Nuovo prodotto</a></li>
                <li class="col-2 invisible">.---------</li>
                <li class="col-2">
                    <a href="login">
                        <input type="button" value="Login" class="loginButton">
                    </a>
                </li>
                
            </ul>
        </nav>

        <h2>Film in evidenza</h2>
        <main class="col-8">
            <article class="card col-2">
                <img src="img/products/pulp-fiction1.jpg" alt="locandina">
                <h2 id="movieTitle">Pulp Fiction</h2>
                <p>Una misteriosa valigetta al centro delle vicende di un cast stellare</p>
                <p>Durata: 190'</p> 
                <p>Genere: Cult</p>
                <p> Rating: * * * * *</p>
                <p>Prezzo: $ 9,99</p>
                <input type="button"value="Acquista" class="myButton">
            </article>
        </main>
        
        <aside class="col-4">
            <article>
                <img src="img/fakecola.gif" class="adv_img">
            </article>
        </aside>

<jsp:include page="footer.jsp"/> 