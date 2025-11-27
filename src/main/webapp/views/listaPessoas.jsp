<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.edensgarden.model.Pessoa" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Pessoas</title>
    <style>
        /* Centraliza o conteúdo como um documento */
        body {
            font-family: sans-serif;
            margin: 20px auto;
            max-width: 800px;
            padding: 0 10px;
        }

        /* Cabeçalho simples: Título na esq, Saudação na dir */
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 2px solid #ccc;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        /* Formulário em linha simples */
        .form-cadastro {
            background: #f9f9f9;
            padding: 15px;
            border: 1px solid #ddd;
            margin-bottom: 20px;
        }

        input[type="text"], input[type="email"] {
            padding: 5px;
            width: 300px;
            margin-right: 10px;
        }

        input[type="submit"] {
            padding: 6px 15px;
            cursor: pointer;
        }

        /* Tabela padrão acadêmica */
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #eee;
        }

        /* Feedback visual simples */
        tr:hover { background-color: #f5f5f5; }

        .btn-sair { color: red; text-decoration: none; margin-left: 5px; }
    </style>
</head>
<body>

    <header>
        <h2 style="margin: 0;">Gerenciamento de Pessoas</h2>
        <div>
            Logado como: <b>admin</b> | <a href="${pageContext.request.contextPath}/logout" class="btn-sair">Sair</a>
        </div>
    </header>

    <div class="form-cadastro">
        <h3>Nova Pessoa</h3>
        <form action="pessoas?acao=adicionar" method="post">
            <input type="text" name="nome" placeholder="Nome" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="submit" value="Salvar">
        </form>
    </div>

    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;">
        <h3 style="margin: 0;">Registros</h3>
        <a href="pessoas?acao=relatorio" target="_blank" style="text-decoration: none; background-color: #28a745; color: white; padding: 5px 10px; border-radius: 4px; font-size: 14px;">Imprimir PDF</a>
    </div>
    <table>
        <tr>
            <th style="width: 40px;">#</th>
            <th>Nome</th>
            <th>Email</th>
            <th style="width: 80px;">Opções</th>
        </tr>
        <%
            List<Pessoa> listaPessoas = (List<Pessoa>) request.getAttribute("listaPessoas");
            if (listaPessoas != null && !listaPessoas.isEmpty()) {
                int count = 1;
                for (Pessoa p : listaPessoas) {
        %>
        <tr>
            <td><%= count++ %></td>
            <td><%= p.getName() %></td>
            <td><%= p.getEmail() %></td>
            <td style="text-align: center;">
                <a href="pessoas?acao=remover&id=<%= p.getId() %>" onclick="return confirm('Remover este item?')">Excluir</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="4" style="text-align: center;">Nenhum registro encontrado.</td></tr>
        <%
            }
        %>
    </table>

</body>
</html>