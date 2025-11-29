package br.com.edensgarden.controller;

import br.com.edensgarden.model.Pessoa;
import br.com.edensgarden.model.PessoaRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/pessoas")
public class PessoaServlet extends HttpServlet {

    private PessoaRepository pessoaRepository = new PessoaRepository();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "listar";
        }

        switch (acao) {
            case "adicionar":
                adicionarPessoa(request, response);
                break;
            case "remover":
                removerPessoa(request, response);
                break;
            case "relatorio": // NOVO CASO
                gerarRelatorio(request, response);
                break;
            default:
                listarPessoas(request, response);
                break;
        }
    }

    private void listarPessoas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Pessoa> listaDePessoas = pessoaRepository.listarTodos();
        request.setAttribute("listaPessoas", listaDePessoas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listaPessoas.jsp");
        dispatcher.forward(request, response);
    }

    private void adicionarPessoa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        Pessoa novaPessoa = new Pessoa(nome, email);
        pessoaRepository.adicionar(novaPessoa);
        response.sendRedirect("pessoas");
    }

    private void removerPessoa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idStr = request.getParameter("id");
        try {
            Long id = Long.parseLong(idStr);
            pessoaRepository.remover(id);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter ID: " + idStr);
        }
        response.sendRedirect("pessoas");
    }

    //JASPER REPORTS
    private void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            // 1. Buscar os dados no banco
            List<Pessoa> lista = pessoaRepository.listarTodos();

            // 2. Transformar em um DataSource do Jasper
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);

            // 3. Carregar layout do arquivo .jrxml
            InputStream jrxmlStream = getClass().getResourceAsStream("/pessoas.jrxml");
            if (jrxmlStream == null) {
                throw new RuntimeException("Arquivo pessoas.jrxml não encontrado em src/main/resources");
            }

            // 4. Compilar o relatório
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

            // 5. Preencher com os dados
            Map<String, Object> parametros = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

            // 6. Configurar a resposta para ser um PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=relatorio_pessoas.pdf");

            // 7. Escrever o PDF na saída
            ServletOutputStream out = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            out.flush();
            out.close();

        } catch (JRException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao gerar relatório PDF", e);
        }
    }
}