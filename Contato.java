import java.io.Serializable;

public abstract class Contato implements Serializable {

    private String nome;
    private String telefone;

    public Contato(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String toString() {
        String resposta = "";
        resposta += "Nome: " + this.nome + "\n";
        resposta += "Telefone: " + this.telefone + "\n";

        return resposta;
    }

}
