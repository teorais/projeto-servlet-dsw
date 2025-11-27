<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        /* Centraliza verticalmente e horizontalmente */
        body {
            font-family: sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        /* Caixa de login simples */
        .login-box {
            border: 1px solid #ccc;
            padding: 30px;
            width: 300px;
            text-align: center;
            border-radius: 5px;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            box-sizing: border-box; /* Garante que o padding não estoure a largura */
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #ddd;
            border: 1px solid #999;
            cursor: pointer;
            margin-top: 10px;
        }
        input[type="submit"]:hover { background-color: #ccc; }

        .msg { margin-bottom: 15px; padding: 10px; font-size: 14px; }
        .error { color: white; background-color: #d9534f; }
        .success { color: #3c763d; background-color: #dff0d8; border: 1px solid #d6e9c6; }
    </style>
</head>
<body>

    <div class="login-box">
        <h2 style="margin-top: 0;">Acesso ao Sistema</h2>

        <% if (request.getParameter("error") != null) { %>
            <div class="msg error">Dados inválidos.</div>
        <% } %>

        <% if (request.getParameter("logout") != null) { %>
            <div class="msg success">Logout efetuado.</div>
        <% } %>

        <form action="login" method="post">
            <input type="text" name="username" placeholder="Usuário" required autofocus>
            <input type="password" name="password" placeholder="Senha" required>
            <input type="submit" value="Entrar">
        </form>
    </div>

</body>
</html>