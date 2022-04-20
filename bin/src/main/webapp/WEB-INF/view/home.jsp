<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/home.css"/>
</head>
<body>
    <header class="top-bar">
            <p>Online Shopping Cart</p>
            <button class="logout" onclick="openPage('/logout')" style="position: absolute;right: 20px;top: 15px;cursor: pointer;background-color: teal;border: none;color: white;border-radius: 10px;padding: 10px;">logout</button>
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
 </script>
</body>
</html>
