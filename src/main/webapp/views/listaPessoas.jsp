<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.edensgarden.model.Pessoa" %>
<html>
<head>
    <title>Gerenciador de Pessoas</title>
    <style>
        body { font-family: sans-serif; }
        table { border-collapse: collapse; width: 60%; margin-top: 20px; }
        th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }
        th { background-color: #f2f2f2; }
        .form-container { border: 1px solid #ccc; padding: 20px; width: 56%; }
    </style>
</head>
<body>

    <h2>Adicionar Nova Pessoa</h2>
    <div class="form-container">
        <form action="pessoas?acao=adicionar" method="post">
            Nome: <input type="text" name="nome" required>
            Email: <input type="email" name="email" required>
            <input type="submit" value="Adicionar">
        </form>
    </div>

    <h2>Lista de Pessoas</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Ação</th>
        </tr>
        <%
            List<Pessoa> listaPessoas = (List<Pessoa>) request.getAttribute("listaPessoas");
            if (listaPessoas != null) {
                for (Pessoa p : listaPessoas) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getName() %></td>
            <td><%= p.getEmail() %></td>
            <td>
                <a href="pessoas?acao=remover&id=<%= p.getId() %>">Remover</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>

</body>
</html>