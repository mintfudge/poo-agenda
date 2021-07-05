public class Amigo extends Contato {

    private String apelido;

    public Amigo(String nome, String telefone, String apelido) {
        super(nome, telefone);
        this.apelido = apelido;
    }

    public String getApelido() {
        return this.apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String toString() {
        String resposta = super.toString();
        resposta += "Apelido: " + this.apelido + "\n";

        return resposta;
    }

}
