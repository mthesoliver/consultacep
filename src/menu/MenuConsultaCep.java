package menu;

import api.ApiViaCepSearch;

import java.io.IOException;
import java.util.Scanner;

public class MenuConsultaCep {
    private ApiViaCepSearch search = new ApiViaCepSearch();
    private Scanner input = new Scanner(System.in);
    private String options = "";

    public MenuConsultaCep() throws IOException, InterruptedException {
    }

    public void acessarMenu() throws IOException, InterruptedException {

        while (!options.equals("0")) {
            System.out.println("Digite o CEP do endereço que deseja consultar:");
            String busca = this.input.nextLine();

            if (busca.contains("-")) {
                String buscaCorrection = busca.replace("-", "");
                this.search.setBusca(buscaCorrection);
            } else if (busca.length() != 8) {
                System.out.println("CEP não encontrado ou incorreto");
                this.search.setBusca(null);
            } else {
                this.search.setBusca(busca);
            }
            this.search.searchByCep(this.search.getBusca());
            System.out.println("Para nova pesquisa digite: '1'");
            System.out.println("Para ver a lista de pesquisas digite: '2'");
            System.out.println("Para sair digite: '0'");
            this.options = input.nextLine();
            if (options.equals("2")) {
                this.search.verLista();
                System.out.println("Deseja fazer nova consulta?");
                System.out.println("Digite '1' para continuar ou '0' para sair");
                this.options = input.nextLine();
                if(options.equals("0")){
                    break;
                }else{
                    System.out.println("Continuar...");
                }
            } else if (options.equals("0")) {
                break;
            } else {
                System.out.println("Pesquisar novamente...");
            }
        }

        System.out.println("Obrigado por fazer sua busca!");
        System.out.println("Exportando sua lista...");
        this.search.addressExporter();
    }

}
