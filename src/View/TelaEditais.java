/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
* Esta classe representa a tela de visualização e interação com os editais.
* Ela herda as propriedades e comportamentos da classe JFrame.
*/
package View;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.EditalController;
import Model.Edital;

/**
* Esta classe representa a tela de visualização e interação com os editais.
* Ela herda as propriedades e comportamentos da classe JFrame.
*/

public class TelaEditais extends JFrame {
	private EditalController editalController;

	/**
	 * Construtor da classe TelaEditais.
	 * Inicializa o controlador de edital e configura a janela.
	 */
	public TelaEditais() {
	    editalController = new EditalController();

	    // Configurações da janela de Editais
	    setTitle("Tela de Editais");
	    setSize(300, 400);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    // Painel principal da janela
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Definindo o layout vertical
	    getContentPane().add(panel);

	    // Painel de preenchimento no topo
	    panel.add(Box.createVerticalGlue());

	    // Botão "Criar Edital"
	    JButton btnCriarEdital = new JButton("Criar Edital");
	    btnCriarEdital.addActionListener(e -> {
	        criarEdital();
	    });
	    panel.add(btnCriarEdital);

	    // Botão "Listar Editais"
	    JButton btnListarEditais = new JButton("Listar Editais");
	    btnListarEditais.addActionListener(e -> {
	        listarEditais();
	    });
	    panel.add(btnListarEditais);

	    // Botão "Listar Editais Abertos"
	    JButton btnListarEditaisAbertos = new JButton("Listar Editais Abertos");
	    btnListarEditaisAbertos.addActionListener(e -> {
	        listarEditaisAbertos();
	    });
	    panel.add(btnListarEditaisAbertos);

	    // Botão "Remover Edital"
	    JButton removerEditalButton = new JButton("Remover Edital");
	    removerEditalButton.addActionListener(e -> {
	        removerEdital();
	    });
	    panel.add(removerEditalButton);

	    // Botão "Pesquisar Edital"
	    JButton pesquisarEditalButton = new JButton("Pesquisar Edital");
	    pesquisarEditalButton.addActionListener(e -> {
	        pesquisarEdital();
	    });
	    panel.add(pesquisarEditalButton);

	    // Botão "Editar Edital"
	    JButton btnEditarEdital = new JButton("Editar Edital");
	    btnEditarEdital.addActionListener(e -> {
	        editarEdital();
	    });
	    panel.add(btnEditarEdital);

	    // Painel de preenchimento na parte inferior
	    panel.add(Box.createVerticalGlue());
	}




	/**
	 * Cria um novo edital com base nas informações fornecidas.
	 */
	public void criarEdital() {
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(7, 2)); // Layout de grid com 7 linhas e 2 colunas

	    // Campos de texto para inserir as informações do edital
	    JTextField nomeField = new JTextField();
	    JTextField dataInicioField = new JTextField();
	    JTextField dataFimField = new JTextField();
	    JTextField dataProvaField = new JTextField();
	    JTextField valorInscricaoField = new JTextField();
	    JTextField escolaridadeField = new JTextField();
	    JTextField numVagasField = new JTextField();

	    // Adicione os campos de texto ao painel
	    panel.add(new JLabel("Nome:")); // Rótulo para o campo "Nome"
	    panel.add(nomeField);
	    panel.add(new JLabel("Data de Início das Inscrições:")); // Rótulo para o campo "Data de Início das Inscrições"
	    panel.add(dataInicioField);
	    panel.add(new JLabel("Data de Fim das Inscrições:")); // Rótulo para o campo "Data de Fim das Inscrições"
	    panel.add(dataFimField);
	    panel.add(new JLabel("Data da Prova:")); // Rótulo para o campo "Data da Prova"
	    panel.add(dataProvaField);
	    panel.add(new JLabel("Valor da Inscrição:")); // Rótulo para o campo "Valor da Inscrição"
	    panel.add(valorInscricaoField);
	    panel.add(new JLabel("Escolaridade:")); // Rótulo para o campo "Escolaridade"
	    panel.add(escolaridadeField);
	    panel.add(new JLabel("Número de Vagas:")); // Rótulo para o campo "Número de Vagas"
	    panel.add(numVagasField);

	    // Mostra o JOptionPane com os campos de texto e botões OK e Cancelar
	    //A função showConfirmDialog retorna um valor inteiro que representa a opção selecionada pelo usuário que é Ok, Cancel ou Closed
	    int result = JOptionPane.showConfirmDialog(null, panel, "Criar Edital", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    //Se result for Ok, os atributos recebem as resposta do usuario
	    if (result == JOptionPane.OK_OPTION) {
	        // Obtém os valores dos campos de texto
	        String nome = nomeField.getText();
	        String dataInicioInscricao = dataInicioField.getText();
	        String dataFimInscricao = dataFimField.getText();
	        String dataProva = dataProvaField.getText();
	        double valorInscricao = Double.parseDouble(valorInscricaoField.getText());
	        String escolaridade = escolaridadeField.getText();
	        int numVagas = Integer.parseInt(numVagasField.getText());

	        // Cria uma instância de Edital com as informações fornecidas
	        Edital novoEdital = new Edital(nome, dataInicioInscricao, dataFimInscricao, dataProva, valorInscricao, escolaridade, numVagas);

	        // Adiciona o novo Edital à lista de Editais no EditalController
	        editalController.adicionarEdital(novoEdital);

	        JOptionPane.showMessageDialog(null, "Edital criado com sucesso!"); // Exibe uma mensagem informando que o edital foi criado com sucesso
	    }
	}

	/**
	 * Lista todos os editais cadastrados.
	 */
	public void listarEditais() {
	    // Obtenha a lista de editais do EditalController
	    List<Edital> editais = editalController.getEditais();

	    // Verifique se existem editais
	    if (editais.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Não existem editais cadastrados.");
	        return;
	    }

	    // Construa a representação dos editais (por exemplo, em uma string)
	    StringBuilder sb = new StringBuilder();
	    for (Edital edital : editais) {
	        sb.append("Nome: ").append(edital.getNome()).append("\n"); // Nome do edital
	        sb.append("Data de Abertura: ").append(edital.getDataInicioInscricao()).append("\n"); // Data de abertura do edital
	        sb.append("Data de Encerramento: ").append(edital.getDataFimInscricao()).append("\n"); // Data de encerramento do edital
	        sb.append("Data da Prova: ").append(edital.getDataProva()).append("\n"); // Data da prova do edital
	        sb.append("Valor da Inscrição: ").append(edital.getValorInscricao()).append("\n"); // Valor da inscrição do edital
	        sb.append("Escolaridade: ").append(edital.getEscolaridade()).append("\n"); // Escolaridade exigida pelo edital
	        sb.append("Numero de Vagas: ").append(edital.getNumVagas()).append("\n"); // Número de vagas do edital

	        sb.append("\n");
	    }

	    // Exiba os editais em uma caixa de diálogo
	    JOptionPane.showMessageDialog(this, sb.toString(), "Lista de Editais", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Lista os editais abertos para inscrição na data atual.
	 */
	public void listarEditaisAbertos() {
	    // Obtenha a data atual
	    LocalDate dataAtual = LocalDate.now();

	    // Obtenha a lista de editais do EditalController
	    List<Edital> editais = editalController.getEditais();

	    // Verifique se existem editais abertos na data atual
	    List<Edital> editaisAbertos = new ArrayList<>();
	    for (Edital edital : editais) {
	        if (dataAtual.isAfter(LocalDate.parse(edital.getDataInicioInscricao())) && dataAtual.isBefore(LocalDate.parse(edital.getDataFimInscricao()))) {
	            editaisAbertos.add(edital);
	        }
	    }

	    // Verifique se existem editais abertos
	    if (editaisAbertos.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Não existem editais abertos para inscrição na data atual.");
	        return;
	    }

	    // Construa a representação dos editais abertos (por exemplo, em uma string)
	    StringBuilder sb = new StringBuilder();
	    for (Edital edital : editaisAbertos) {
	        sb.append("Nome: ").append(edital.getNome()).append("\n"); // Nome do edital aberto
	        sb.append("Data de Abertura: ").append(edital.getDataInicioInscricao()).append("\n"); // Data de abertura do edital
	        sb.append("Data de Encerramento: ").append(edital.getDataFimInscricao()).append("\n"); // Data de encerramento do edital
	        sb.append("Data da Prova: ").append(edital.getDataProva()).append("\n");
	        sb.append("Valor: ").append(edital.getValorInscricao()).append("\n");
	        sb.append("Nivel de escolaridade: ").append(edital.getEscolaridade()).append("\n");
	        sb.append("Quantidade de vagas: ").append(edital.getNumVagas()).append("\n");
	        sb.append("\n");
	    }

	    // Exiba os editais abertos em uma caixa de diálogo
	    JOptionPane.showMessageDialog(this, sb.toString(), "Lista de Editais Abertos", JOptionPane.INFORMATION_MESSAGE);
	}

    
	/**
	 * Busca um edital pelo nome.
	 *
	 * @param nomeEdital O nome do edital a ser buscado.
	 * @return O objeto Edital correspondente ao nome fornecido, ou null caso não seja encontrado.
	 */
	private Edital buscarEditalPorNome(String nomeEdital) {
	    // Percorre a lista de editais obtida do editalController
	    for (Edital edital : editalController.getEditais()) {
	        // Verifica se o nome do edital atual é igual ao nome fornecido como parâmetro
	        if (edital.getNome().equals(nomeEdital)) {
	            // Retorna o edital encontrado
	            return edital;
	        }
	    }
	    // Caso nenhum edital com o nome fornecido seja encontrado, retorna null
	    return null;
	}


	/**
	 * Remove um edital.
	 * Solicita ao usuário o nome do edital a ser removido, busca o edital correspondente e realiza a remoção.
	 * Exibe mensagens de confirmação e resultado da operação.
	 */
	public void removerEdital() {
	    // Solicita ao usuário o nome do edital a ser removido
	    String nomeEdital = JOptionPane.showInputDialog(null, "Digite o nome do edital que deseja remover:");

	    // Busca o edital pelo nome fornecido
	    Edital edital = buscarEditalPorNome(nomeEdital);

	    if (edital != null) {
	        // Confirmação da remoção com o usuário
	        int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o edital:\n" + edital.toString(), "Confirmação de Remoção", JOptionPane.YES_NO_OPTION);

	        if (confirmacao == JOptionPane.YES_OPTION) {
	            // Remove o edital utilizando o EditalController
	            editalController.removerEdital(edital);
	            JOptionPane.showMessageDialog(null, "Edital removido com sucesso!");
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Edital não encontrado!");
	    }
	}

    
	/**
	 * Realiza a pesquisa de um edital.
	 * Solicita ao usuário o nome do edital a ser pesquisado e exibe o resultado da pesquisa.
	 * Caso o edital seja encontrado, exibe os detalhes do edital.
	 * Caso contrário, exibe uma mensagem informando que o edital não foi encontrado.
	 */
	public void pesquisarEdital() {
	    // Solicita ao usuário o nome do edital a ser pesquisado
	    String nomeEdital = JOptionPane.showInputDialog(null, "Digite o nome do edital que deseja pesquisar:");

	    // Busca o edital pelo nome fornecido
	    Edital edital = buscarEditalPorNome(nomeEdital);

	    if (edital != null) {
	        // Exibe os detalhes do edital encontrado
	        JOptionPane.showMessageDialog(null, edital.toString(), "Edital Encontrado", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        // Exibe uma mensagem informando que o edital não foi encontrado
	        JOptionPane.showMessageDialog(null, "Edital não encontrado!", "Edital não Encontrado", JOptionPane.WARNING_MESSAGE);
	    }
	}

	/**
	 * Realiza a edição de um edital existente.
	 * Solicita ao usuário o nome do edital a ser editado e exibe um painel com os campos de texto preenchidos
	 * com as informações atuais do edital. Permite ao usuário editar as informações do edital e salvar as alterações.
	 * Caso o edital seja encontrado e as alterações sejam confirmadas, as informações do edital são atualizadas.
	 * Caso contrário, exibe uma mensagem informando que o edital não foi encontrado.
	 */
	public void editarEdital() {
	    // Solicita ao usuário o nome do edital a ser editado
	    String nomeEdital = JOptionPane.showInputDialog(null, "Digite o nome do edital a ser editado:");
	    
	    // Busca o edital pelo nome fornecido
	    Edital edital = buscarEditalPorNome(nomeEdital);

	    if (edital != null) {
	        // Cria um painel para a edição do edital
	        JPanel panel = new JPanel();
	        panel.setLayout(new GridLayout(7, 2)); // Layout com 7 linhas e 2 colunas

	        // Cria campos de texto preenchidos com as informações atuais do edital
	        JTextField nomeField = new JTextField(edital.getNome());
	        JTextField dataInicioField = new JTextField(edital.getDataInicioInscricao());
	        JTextField dataFimField = new JTextField(edital.getDataFimInscricao());
	        JTextField dataProvaField = new JTextField(edital.getDataProva());
	        JTextField valorInscricaoField = new JTextField(Double.toString(edital.getValorInscricao()));
	        JTextField escolaridadeField = new JTextField(edital.getEscolaridade());
	        JTextField numVagasField = new JTextField(Integer.toString(edital.getNumVagas()));

	        // Adiciona os campos de texto ao painel
	        panel.add(new JLabel("Nome:"));
	        panel.add(nomeField);
	        panel.add(new JLabel("Data de Início das Inscrições:"));
	        panel.add(dataInicioField);
	        panel.add(new JLabel("Data de Fim das Inscrições:"));
	        panel.add(dataFimField);
	        panel.add(new JLabel("Data da Prova:"));
	        panel.add(dataProvaField);
	        panel.add(new JLabel("Valor da Inscrição:"));
	        panel.add(valorInscricaoField);
	        panel.add(new JLabel("Escolaridade:"));
	        panel.add(escolaridadeField);
	        panel.add(new JLabel("Número de Vagas:"));
	        panel.add(numVagasField);

	        // Exibe o JOptionPane com os campos de texto e botões OK/Cancelar
	        int result = JOptionPane.showConfirmDialog(null, panel, "Editar Edital", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

	        if (result == JOptionPane.OK_OPTION) {
	            // Atualiza as informações do edital com os valores fornecidos
	            edital.setNome(nomeField.getText());
	            edital.setDataInicioInscricao(dataInicioField.getText());
	            edital.setDataFimInscricao(dataFimField.getText());
	            edital.setDataProva(dataProvaField.getText());
	            edital.setValorInscricao(Double.parseDouble(valorInscricaoField.getText()));
	            edital.setEscolaridade(escolaridadeField.getText());
	            edital.setNumVagas(Integer.parseInt(numVagasField.getText()));

	            JOptionPane.showMessageDialog(null, "Edital editado com sucesso!");
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Edital não encontrado!");
	    }
	}

}