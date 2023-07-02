/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe responsável por exibir a tela principal do aplicativo.
 */
public class TelaPrincipal extends JFrame {
    /**
     * Construtor da classe TelaPrincipal.
     * Configura a janela e adiciona os botões de Editais e Questões.
     */
    public TelaPrincipal() {
        // Configurações da janela
        setTitle("Meu Aplicativo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal da janela
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        // Botão de Editais
        JButton btnEditais = new JButton("Editais");
        btnEditais.addActionListener(e -> {
            // Abrir a tela de Editais
            TelaEditais telaEditais = new TelaEditais();
            telaEditais.setVisible(true);
        });
        panel.add(btnEditais);

        // Botão de Questões
        JButton btnQuestoes = new JButton("Questões");
        btnQuestoes.addActionListener(e -> {
            // Abrir a tela de Questões
            TelaQuestoes telaQuestoes = new TelaQuestoes();
            telaQuestoes.setVisible(true);
        });
        panel.add(btnQuestoes);
    }

    /**
     * Método principal que cria uma instância da classe TelaPrincipal
     * e exibe a janela principal.
     *
     * @param args os argumentos de linha de comando (não utilizados neste caso)
     */
    public static void main(String[] args) {
        TelaPrincipal telaPrincipal = new TelaPrincipal();

        // Exibir a janela
        telaPrincipal.setVisible(true);
    }
}