package br.com.edensgarden.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class PessoaRepository {

    private static final String API_URL = "http://localhost:3000/pessoas";

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public List<Pessoa> listarTodos(){
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Type tipoListaPessoas = new TypeToken<List<Pessoa>>(){}.getType();
            return gson.fromJson(response.body(), tipoListaPessoas);

        } catch(IOException | InterruptedException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void adicionar(Pessoa novaPessoa){
        try {
            String requestBody = gson.toJson(novaPessoa);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    public void remover(String id){
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + "/" + id))
                    .DELETE()
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}