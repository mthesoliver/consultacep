package api;

import enderecos.Endereco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enderecos.exporter.EnderecoExporter;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiViaCepSearch extends ApiViaCepConnection implements EnderecoExporter {
    private String apiViaCep = "https://viacep.com.br/ws/";
    private String busca;
    private String extension = "/json/";
    private List<Endereco> listaCeps = new ArrayList<>();
    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    private Endereco endereco = new Endereco(this.busca);

    public ApiViaCepSearch() throws IOException, InterruptedException {
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public List<Endereco> getListaCeps() {
        return listaCeps;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public String getBusca() {
        return busca;
    }

    public String getApiViaCep() {
        return apiViaCep;
    }

    public void setApiViaCep(String apiViaCep) {
        this.apiViaCep = apiViaCep;
    }



    public String searchByCep(String busca) throws IOException, InterruptedException {
        this.busca = busca;
        String url = this.apiViaCep + this.busca + this.extension;
            try {
                request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();
                response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                this.endereco = gson.fromJson(response.body(), Endereco.class);
                System.out.println("\nVocê consultou por: \n" + this.endereco);
                this.listaCeps.add(this.endereco);
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
                throw new RuntimeException();
            }
            return response.body();
    }

    public void verLista(){
        System.out.println("\nSua lista de consultas é: \n" + this.listaCeps);
    }

    @Override
    public void addressExporter() throws IOException {
        FileWriter escrita = new FileWriter("endereçosConsultados.json");
        escrita.write(gson.toJson(listaCeps));
        escrita.close();
    }
}
