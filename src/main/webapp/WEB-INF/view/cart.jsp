<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Shopping Cart UI</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/cart.css"/>
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,900" rel="stylesheet">
</head>
<body>
   <div class="CartContainer">
   	   <div class="Header">
   	   	<h3 class="Heading">Shopping Cart</h3>
   	   	<h5 class="Action" onclick="removeAll()">Remove all</h5>
   	   </div>

 <c:forEach items="${cartItems}" var="cartItem">
                 <div class="Cart-Items">
                    	   	  <div class="image-box">
                    	   	  	<img src="data:image/jpg;base64,${cartItem.imageBase64}" style="height:120px" />
                    	   	  </div>
                    	   	  	<h1 class="title">${cartItem.itemName}</h1>
                    	   	  <div class="prices">
                    	   	  	<div class="amount">Rs.${cartItem.amount}</div>
                    	   	  	<div class="remove"><u onclick="openDetail('/removeItem?item=${cartItem}')">Remove</u></div>
                    	   	  </div>
                 </div>
        </c:forEach>
   	 <hr>
   	 <div class="checkout">
   	 <div class="total">
   	 	<div>
   	 		<div class="Subtotal">Sub-Total</div>
   	 		<div class="items">${noOfItems} items</div>
   	 	</div>
   	 	<div class="total-amount">Rs.${totalAmount}</div>
   	 </div>
   	 <button class="button" onclick="sendMail();">Checkout</button></div>
   </div>
    <script type="text/javascript">
            function removeAll()
            {
            window.location.href = "/removeAll";
            }
            function openDetail(pageURL){
            window.location.href = pageURL;
            }
            function sendMail(productId)
          {
            alert("Mail sent successfully!!!!");
            window.location.href = "/sendEmail";
          }

    </script>
</body>
</html>