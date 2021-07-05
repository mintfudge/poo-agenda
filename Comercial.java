public class Comercial extends Contato {

    private String email;

    public Comercial(String nome, String telefone, String email) {
        super(nome, telefone);
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        String resposta = super.toString();
        resposta += "E-mail: " + this.email + "\n";

        return resposta;
    }

}
