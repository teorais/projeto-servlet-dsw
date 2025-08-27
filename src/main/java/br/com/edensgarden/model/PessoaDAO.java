package br.com.edensgarden.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PessoaDAO {

    private static final List<Pessoa> bancoDeDados = new ArrayList<>();
    private static final AtomicInteger sequence = new AtomicInteger(1);

    // Bloco estático para popular a lista com alguns dados iniciais
    static {
        bancoDeDados.add(new Pessoa(sequence.getAndIncrement(), "Ada Lovelace", "ada.lovelace@example.com"));
        bancoDeDados.add(new Pessoa(sequence.getAndIncrement(), "Grace Hopper", "grace.hopper@example.com"));
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