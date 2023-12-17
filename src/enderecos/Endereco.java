package enderecos;

public class Endereco {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public Endereco(String cep) {
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    @Override
    public String toString() {
        return "\nResultado da cosulta do CEP: " + cep +
                "\nRUA: " + logradouro +
                "\nBAIRRO: " + bairro +
                "\nCIDADE: " + localidade +
                "\nESTADO: " + uf + "\n";
    }
}

