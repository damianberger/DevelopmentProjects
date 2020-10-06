<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header class="header-main-page">
    <nav class="header-container">
        <ul class="account-actions">
            <li><a href="/register">Załóż konto</a></li>
            <li><a href="/login">Zaloguj</a></li>
        </ul>

        <ul class="main-page-menu">
            <li>
                <button id="tech-button">techy</button></li>
            <li><button id="frame-button">frame`y</button></li></li>
            <li><a href="/user">usery</a></li>
            <li><a href="/role">role</a></li>
        </ul>
    </nav>
    <section class="content">

    </section>
</header>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
