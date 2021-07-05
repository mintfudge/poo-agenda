public class Familiar extends Contato {

    private String parentesco;

    public Familiar(String nome, String telefone, String parentesco) {
        super(nome, telefone);
        this.parentesco = parentesco;
    }

    public String getParentesco() {
        return this.parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String toString() {
        String resposta = super.toString();
        resposta += "Parentesco: " + this.parentesco + "\n";

        return resposta;
    }

}
