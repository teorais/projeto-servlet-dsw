package br.com.edensgarden.model;

import br.com.edensgarden.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class PessoaRepository {

    public void adicionar(Pessoa pessoa) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin(); // Inicia a transação
            em.persist(pessoa);          // Salva no banco
            em.getTransaction().commit(); // Confirma a transação
        } catch (Exception e) {
            em.getTransaction().rollback(); // Desfaz se der erro
            throw e;
        } finally {
            em.close(); // Fecha o EntityManager
        }
    }

    public List<Pessoa> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // JPQL: "SELECT p FROM Pessoa p" (Pessoa é a Classe, não a tabela)
            return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void remover(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Pessoa pessoa = em.find(Pessoa.class, id); // Busca a pessoa pelo ID
            if (pessoa != null) {
                em.remove(pessoa); // Remove do banco
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}