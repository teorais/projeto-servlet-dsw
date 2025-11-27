<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - Gerenciador</title>
    <style>
        body { font-family: sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; background-color: #f0f2f5; }
        .login-container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); width: 300px; text-align: center; }
        input[type="text"], input[type="password"] { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        input[type="submit"] { background-color: #007bff; color: white; border: none; padding: 10px; width: 100%; border-radius: 4px; cursor: pointer; }
        input[type="submit"]:hover { background-color: #0056b3; }
        .error { color: red; margin-bottom: 10px; font-size: 0.9em; }
        .msg { color: green; margin-bottom: 10px; font-size: 0.9em; }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Acesso Restrito</h2>

    <% if (request.getParameter("error") != null) { %>
        <div class="error">Usuário ou senha inválidos!</div>
    <% } %>
    <% if (request.getParameter("logout") != null) { %>
        <div class="msg">Você saiu do sistema.</div>
    <% } %>

    <form action="login" method="post">
        <input type="text" name="username" placeholder="Usuário (admin)" required>
        <input type="password" name="password" placeholder="Senha (123456)" required>
        <input type="submit" value="Entrar">
    </form>
</div>

</body>
</html>