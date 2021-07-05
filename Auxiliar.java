import javax.swing.JOptionPane;

public class Auxiliar {

    private static boolean intValido(String s, int min, int max) {
        try {
            int num = Integer.parseInt(s);
            if (num < min || num > max) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int retornaInteiro(String entrada, String menu, int min, int max) {
        if (entrada == null) {
            // verifica se o usuário clicou em cancelar, se sim, define para execução 0 (voltar/sair)
            entrada = "0";
        }
        while (!Auxiliar.intValido(entrada, min, max)) {
            JOptionPane.showMessageDialog(null, "Opção inválida!" + entrada);
            entrada = JOptionPane.showInputDialog(null, menu);
        }
        return Integer.parseInt(entrada);
    }

    public static String[] captaDados (String [] campos){
		String [] dados = new String [campos.length];

		for (int i = 0; i < campos.length; i++)
			dados[i] = JOptionPane.showInputDialog  (campos[i]+ ": ");

		return dados;
	}

}
