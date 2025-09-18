package br.com.edensgarden.controller;

import br.com.edensgarden.model.Pessoa;
import br.com.edensgarden.model.PessoaRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/pessoas")
public class PessoaServlet extends HttpServlet {

    private PessoaRepository pessoaRepository = new PessoaRepository();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Pega o parâmetro 'acao' da URL. Ex: /pessoa?acao=remover
        String acao = request.getParameter("acao");

        // Se nenhuma ação for especificada, a ação padrão é "listar"
        if (acao == null) {
            acao = "listar";
        }

        // Decide qual método chamar com base na ação
        switch (acao) {
            case "adicionar":
                adicionarPessoa(request, response);
                break;
            case "remover":
                removerPessoa(request, response);
                break;
            default: // "listar" ou qualquer outra coisa
                listarPessoas(request, response);
                break;
        }
    }

    private void listarPessoas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Pessoa> listaDePessoas = pessoaRepository.listarTodos();
        request.setAttribute("listaPessoas", listaDePessoas); // Envia a lista para o JSP

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listaPessoas.jsp");
        dispatcher.forward(request, response);
    }

    private void adicionarPessoa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");

        Pessoa novaPessoa = new Pessoa(nome, email);
        pessoaRepository.adicionar(novaPessoa);

        // Redireciona o usuário de volta para a página de listagem
        response.sendRedirect("pessoas");
    }

    private void removerPessoa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String id = request.getParameter("id");
        pessoaRepository.remover(id);

        // Redireciona o usuário de volta para a página de listagem
        response.sendRedirect("pessoas");
    }
}