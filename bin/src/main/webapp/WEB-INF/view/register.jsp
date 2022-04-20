<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/register.css"/>
</head>
<body>
	 <div class="register">

        <div class="register-card">
            <span class="registerTitle">Register</span>
              <form class="registerForm" method="post">
                        <label>Username</label>
                        <input
                          type="text"
                          class="registerInput"
                          placeholder="Enter your username..."
                          name="userName"
                        />
                        <label>Email</label>
                        <input
                          type="text"
                          class="registerInput"
                          placeholder="Enter your email..."
                          name="email"
                        />
                        <label>Password</label>
                        <input
                          type="password"
                          class="registerInput"
                          placeholder="Enter your password..."
                          name="password"
                        />
                        <button class="registerButton">
                          Register
                        </button>
                         <p class="error" style="font-size: 20px;text-align: center;margin-bottom: 20px;color: indianred;">${errorMsg}</p>
                      </form>
        </div>
          <button class="registerLoginButton" onclick="openPage('/login')" >
              Login
          </button>
        </div>
        <script type="text/javascript">
         function openPage(pageURL)
         {
         window.location.href = pageURL;
         }
        </script>
</body>
</html>