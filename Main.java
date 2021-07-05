import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();

        main.menu();
    }

    private ArrayList<Contato> agenda = new ArrayList<Contato>();

    public void menu() {
        String menu = "";
        String entrada;
        int opc1, opc2;

        do {
            menu =  "Agenda\nOpções:\n" +
                    "1. Criar Contato\n" +
                    "2. Exibir Contatos\n" +
                    "3. Limpar Contatos\n" +
                    "4. Gravar Contatos\n" +
                    "5. Recuperar Contatos\n" +
                    "0. Sair";
            entrada = JOptionPane.showInputDialog(menu + "\n\n");
            opc1 = Auxiliar.retornaInteiro(entrada, menu, 0, 5);

            switch (opc1) {
                case 1:// Entrar os dados
                    menu = "Entrada de novo contato\n" + "Opções:\n"
                         + "1. Amigo\n"
                         + "2. Comercial\n"
                         + "3. Familiar\n"
                         + "0. Voltar";

                    entrada = JOptionPane.showInputDialog(menu + "\n\n");
                    opc2 = Auxiliar.retornaInteiro(entrada, menu, 0, 3);
                    Boolean add = false;
                    switch (opc2) {
                        case 1:
                            agenda.add((Contato) addAmigo());
                            add = true;
                            break;
                        case 2:
                            agenda.add((Contato) addComercial());
                            add = true;
                            break;
                        case 3:
                            agenda.add((Contato) addFamiliar());
                            add = true;
                            break;
                        default:
                            // JOptionPane.showMessageDialog(null, "Erro ao configurar o validador");
                    }
                    if (add) {
                        JOptionPane.showMessageDialog(null, "Contato adicionado");
                    }
                    break;

                case 2: // Exibir os dados
                    if (agenda.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Não há contatos na memória. Entre com os contatos primeiramente");
                        break;
                    }
                    String dados = "";
                    for (int i = 0; i < agenda.size(); i++) {
                        dados += agenda.get(i).toString() + "---------------\n";
                    }
                    JOptionPane.showMessageDialog(null, dados);
                    break;

                case 3: // Limpar os dados
                    if (agenda.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Não há contatos na memória. Entre com os contatos primeiramente");
                        break;
                    }
                    agenda.clear();
                    JOptionPane.showMessageDialog(null, "Dados LIMPOS com sucesso!");
                    break;

                case 4: // Grava Dados
                    if (agenda.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Não há contatos na memória. Entre com os contatos primeiramente");
                        break;
                    }
                    salvaDados(agenda);
                    JOptionPane.showMessageDialog(null, "Dados SALVOS com sucesso!");
                    break;

                case 5: // Recupera Dados
                    agenda = recuperaDados();
                    if (agenda.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Sem dados para apresentar.");
                        break;
                    }
                    JOptionPane.showMessageDialog(null, "Dados RECUPERADOS com sucesso!");
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Fim do aplicativo");
                    break;
            }
        } while (opc1 != 0);

    }

    private Amigo addAmigo() {
        String [] valores = new String [3];
        String [] campos = {"Nome", "Telefone", "Apelido"};
        valores = Auxiliar.captaDados(campos);

        Amigo amigo = new Amigo(valores[0], valores[1], valores[2]);
        return amigo;
    }

    private Comercial addComercial() {
        String [] valores = new String [3];
        String [] campos = {"Nome", "Telefone", "E-mail"};
        valores = Auxiliar.captaDados(campos);

        Comercial comercial = new Comercial(valores[0], valores[1], valores[2]);
        return comercial;
    }

    private Familiar addFamiliar() {
        String [] valores = new String [3];
        String [] campos = {"Nome", "Telefone", "Parentesco"};
        valores = Auxiliar.captaDados(campos);

        Familiar familiar = new Familiar(valores[0], valores[1], valores[2]);
        return familiar;
    }

	public void salvaDados (ArrayList<Contato> agenda){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("agenda.dados"));
			for (int i=0; i < agenda.size(); i++)
				outputStream.writeObject(agenda.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Não foi possível criar o arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Contato> recuperaDados (){
		ArrayList<Contato> agendaTemp = new ArrayList<Contato>();

		ObjectInputStream inputStream = null;

		try {
			inputStream = new ObjectInputStream (new FileInputStream("agenda.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Contato) {
					agendaTemp.add((Contato) obj);
				}
			}
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "O arquivo de contatos NãO existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return agendaTemp;
		}
	}

}
