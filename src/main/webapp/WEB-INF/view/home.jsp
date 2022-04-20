<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/home.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
</head>
<body>
     <header class="top-bar" style="display: flex;align-items: center;justify-content: space-around;">
            <p>Online Shopping Cart</p>
            <div class="topItems" style="display: flex;align-items: center;">
                <div class="cartWrapper" style="display: flex;margin-right: 30px;position: relative;cursor: pointer;" onclick="openCart()">
                    <i class="fa-solid fa-cart-shopping"></i>
                    <div class="notification" style="  height: 20px;width: 20px;position: absolute;top: -12px;right: -10px;background-color: teal;color: white;font-size: 17px;border-radius: 50%;">${notification}</div>
                </div>

                <button class="logout" onclick="openPage('/logout')" style="cursor: pointer;background-color: teal;border: none;color: white;border-radius: 10px;padding: 10px;">logout</button>
            </div>
        </header>
    <main class="cards" style="margin-bottom: 100px;">
        <c:forEach items="${products}" var="product">
                 <section class="card">
                             <div class="image-wrapper">
                                 <img class="image" src="data:image/jpg;base64,${product.imageBase64}"/>
                             </div>
                             <div class="item-name">
                                 ${product.itemName}
                             </div>
                             <button id=${product.id} onclick="openDetail('/invoice?id=${product.id}')" class="buy-btn">Buy</button>
                 </section>
        </c:forEach>
    </main>
    <footer class="footer" style="position: fixed;bottom: 0px;"> </footer>
 <script type="text/javascript">
         function openPage(pageURL)
         {
         window.location.href = pageURL;
         }
         function openDetail(pageURL){
            window.location.href = pageURL;
         }
         function openCart(){
            window.location.href = "/cart";
         }
 </script>
</body>
</html>
