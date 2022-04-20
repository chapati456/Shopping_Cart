<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Invoice</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/invoice.css"/>
</head>
<body>
    <header class="top-bar">
            <p>Online Shopping Cart</p>
           <button class="logout" onclick="openPage('/logout')" style="position: absolute;right: 20px;top: 15px;cursor: pointer;background-color: teal;border: none;color: white;border-radius: 10px;padding: 10px;">logout</button>
    </header>
    <main class="invoice-wrapper">
        <div class="invoice-image">
            <img class="image" src="data:image/jpg;base64,${productDetails.imageBase64}" alt="">
        </div>
        <div class="invoice-details">
            <div class="invoice-item-name">
                ${productDetails.itemName}
            </div>
            <div class="invoice-amount">
                Rs.100
            </div>
            <button class="invoice-btn" onclick="sendMail(${productDetails.id});">Send-invoice</button>
        </div>
    </main>
    <footer class="footer"> </footer>
    <script type="text/javascript">
             function openPage(pageURL)
             {
               window.location.href = pageURL;
             }
     </script>
     <script type="text/javascript">
                  function sendMail(productId)
                  {
                    alert("Mail sent successfully!!!!");
                    window.location.href = "/sendEmail?id="+productId;
                  }
     </script>
</body>
</html>