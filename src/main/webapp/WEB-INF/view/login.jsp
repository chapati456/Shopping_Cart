<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/login.css"/>
</head>
<body>
	<div class="login">
	<div class="login-card">
	     <span class="loginTitle">Login</span>
                  <form class="loginForm" method="post" action="/login">
                    <label>Username</label>
                    <input
                      type="text"
                      class="loginInput"
                      name="userName"
                      placeholder="Enter your username..."
                    />
                    <label>Password</label>
                    <input
                      type="password"
                      class="loginInput"
                      name="password"
                      placeholder="Enter your password..."
                    />
                    <button class="loginButton">
                      Login
                    </button>
                    <p class="error" style="font-size: 20px;text-align: center;margin-bottom: 20px;color: indianred;">${errorMsg}</p>
                  </form>
                  <button class="loginRegisterButton" onclick="openPage('/register')">
                    Register
              </button>
	    </div>
        </div>

     <script type="text/javascript">
         function openPage(pageURL)
         {
         window.location.href = pageURL;
         }
     </script>
</body>
</html>