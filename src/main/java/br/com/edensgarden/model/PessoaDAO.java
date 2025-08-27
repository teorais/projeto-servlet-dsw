package br.com.edensgarden.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PessoaDAO {

    private static final List<Pessoa> bancoDeDados = new ArrayList<>();
    private static final AtomicInteger sequence = new AtomicInteger(1);

    static {
        bancoDeDados.add(new Pessoa(sequence.getAndIncrement(), "Pessoa Teste 1", "emaildeteste1@email.com"));
        bancoDeDados.add(new Pessoa(sequence.getAndIncrement(), "Pessoa Teste 2", "emaildeteste2@email.com"));
    }

    public void adicionar(Pessoa pessoa) {
        pessoa.setId(sequence.getAndIncrement()); // Define um novo ID único
        bancoDeDados.add(pessoa);
    }

    public void remover(int id) {
        bancoDeDados.removeIf(pessoa -> pessoa.getId() == id);
    }

    public List<Pessoa> listarTodos() {
        return new ArrayList<>(bancoDeDados);
    }
}