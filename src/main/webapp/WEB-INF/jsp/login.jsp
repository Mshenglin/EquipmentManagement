<%--
  Created by IntelliJ IDEA.
  User: hkw
  Date: 2018/11/2
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/usersLogin.css">
    <link rel="icon" href="/images/favicon.ico" sizes="32x32" />
    <script src="/js/jquery-1.3.2.min.js"></script>
    <script src="/js/login.js"></script>

    <title>前台首页</title>
</head>
<body>

<div class="header">

</div>

<div class="body">
    <div class="panel">
        <div class="top">
            <p>账户登陆</p>
        </div>

        <div class="middle">
            <form action="/login" method="post">
                <span class="erro">${msg}</span>
                <span class="s1"></span>
                <span class="s2"></span>
                <input type="text" name="username" value=""  class="inputs"/>
                <input type="password" name="password" value="" class="inputs"/>
                <div id="sign"><a href="/signFirst">没有账号？立即注册</a></div>
                <input type="submit" value="登陆"/>
            </form>
        </div>
    </div>
</div>

<div class="footer">
    <span>@Copyright © 2019-2020 版权所有 </span>
</div>
</body>
</html>
