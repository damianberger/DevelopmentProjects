<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Login</title>
</head>
<body>

<section>
    <h2>Logowanie</h2>
    <form method="post" action="/login">
        <div>
            <input type="text" name="username" placeholder="Username"/>
        </div>
        <div>
            <input type="password" name="password" placeholder="Hasło"/>
        </div>
        <input type="submit" value="Zaloguj"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</section>

</body>
</html>