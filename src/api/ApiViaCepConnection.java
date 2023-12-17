package api;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiViaCepConnection {
    protected HttpClient client = HttpClient.newHttpClient();
    protected HttpRequest request;
    protected HttpResponse<String> response;

    public ApiViaCepConnection() throws IOException, InterruptedException {
    }
}
