package projeto.Atleta.NBA;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Delegação {
    public String[] leValores (String [] dadosIn){
        String [] dadosOut = new String [dadosIn.length];

        for (int i = 0; i < dadosIn.length; i++)
            dadosOut[i] = JOptionPane.showInputDialog("Entre com " + dadosIn[i] + ": ");
        return dadosOut;
    }

    public Guard leGuard (){

        String [] valores = new String [2];
        String [] nomeVal = {"Nomes", "Numero", "Especialidade"};
        valores = leValores (nomeVal);

        Guard atlGuar = new Guard (valores[0],valores[1],valores[2]);
        return atlGuar;
    }

    public Forward leForward () {

        String[] valores = new String[2];
        String[] nomeVal = {"Nomes", "Numero", "Especialidade"};
        valores = leValores(nomeVal);

        Forward atlFor = new Forward (valores[0], valores[1], valores[2]);
        return atlFor;
    }

    public Center leCenter () {

        String[] valores = new String[2];
        String[] nomeVal = {"Nomes", "Numero", "Especialidade"};
        valores = leValores(nomeVal);

        Center atlCent = new Center (valores[0], valores[1], valores[2]);
        return atlCent;
    }

    public void mostraAtleta (String dados){
        JOptionPane.showMessageDialog(null, "ATLETA\n-------\n +" +dados);
    }

    public void salvaAtletas (ArrayList<Atleta> atletas){
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream
                    (new FileOutputStream("C:\\Users\\marce\\Documents\\Global Labs Academy\\ATLETA1\\src\\projeto\\Atleta"));
            for (int i=0; i< atletas.size(); i++)
                outputStream.writeObject(atletas.get(i));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Impossível criar arquivo!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally { //Close the ObjectOutputStream
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


    public ArrayList<Atleta> recuperaAtletas (){
        ArrayList<Atleta> atletas = new ArrayList<Atleta>();

        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream
                    (new FileInputStream("C:\\Users\\marce\\Documents\\Global Labs Academy\\ATLETA1\\src\\projeto\\Atleta"));
            Object obj = null;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof Atleta) {
                    atletas.add((Atleta) obj);
                }
            }
        } catch (EOFException ex) { // when EOF is reached
            System.out.println("End of file reached.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo com produtos não existe");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally { //Close the ObjectInputStream
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
            return atletas;
        }
    }

    public void menuAtletas () {

        ArrayList<Atleta> atletas = new ArrayList<Atleta>();


        String menu = "";
        String entrada;
        int opc1, opc2;

        do {
            menu = "Controle de Delegação\n" +
                    "Opções:\n" +
                    "1. Entrar Atletas\n" +
                    "2. Exibir Atletas\n" +
                    "3. Limpar Atletas\n" +
                    "4. Gravar Atletas\n" +
                    "5. Recuperar Atletas\n" +
                    "9. Sair";
            entrada = JOptionPane.showInputDialog(menu + "\n\n");

            while (!numeroInteiroValido(entrada)) {
                entrada = JOptionPane.showInputDialog(null, menu +
                        "\n\nEntrada inválida! Digite um número inteiro. ");
            }
            opc1 = new Integer(entrada);

            switch (opc1) {
                case 1: // Entrar dados
                    menu = "Entrada de Atletas\n" +
                            "Opções:\n" +
                            "1. Guard/Armador\n" +
                            "2. Forward/Ala\n" +
                            "3. Center/Pivô\n";

                    entrada = JOptionPane.showInputDialog(menu + "\n\n");
                    while (!numeroInteiroValido(entrada)) {
                        entrada = JOptionPane.showInputDialog(null, menu +
                                "\n\nEntrada inválida! Digite um número inteiro.");
                    }
                    opc2 = new Integer(entrada);

                    switch (opc2) {
                        case 1: atletas.add((Atleta) leGuard());
                            break;
                        case 2: atletas.add((Atleta) leForward());
                            break;
                        case 3: atletas.add((Atleta) leCenter());
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Atleta para entrada não escolhido!");
                    }

                    break;
                case 2: // Exibir dados
                    if (atletas.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Entre com atletas primeiramente");
                        break;
                    }
                    String dados = "";
                    for (int i = 0; i < atletas.size(); i++) {
                        dados += atletas.get(i).toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, dados);
                    break;
                case 3: // Limpar dados
                    if (atletas.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Entre com atletas primeiramente");
                        break;
                    }
                    atletas.clear();
                    JOptionPane.showMessageDialog(null, "Dados limpos com sucesso!");
                    break;
                case 4: // Gravar dados
                    if (atletas.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Entre com atletas primeiramente");
                        break;
                    }
                    salvaAtletas(atletas);
                    JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
                    break;
                case 5: // Recupera dados
                    atletas = recuperaAtletas();
                    if (atletas.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Sem dados para apresentar.");
                        break;
                    }
                    JOptionPane.showMessageDialog(null, "Dados recuperados com sucesso!");
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null, "Fim do aplicativo de delegação");
                    break;
            }
        } while (opc1 != 9);
    }

    private boolean numeroInteiroValido(String s) {
        boolean resultado;
        try {
            Integer.parseInt(s);
            resultado = true;
        } catch (NumberFormatException e) {
            resultado = false;
        }
        return resultado;
    }

    public static void main (String [] args){
        Delegação del = new Delegação();
        del.menuAtletas();
    }
}
