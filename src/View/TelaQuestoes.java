/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package View;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Model.Questao;
import Model.QuestaoAlternativa;
import Model.QuestaoVF;
import Controller.QuestaoAlternativaController;
import Controller.QuestaoVFController;

/**
 * Classe responsável por exibir a tela de Questões.
 * A tela possui funcionalidades para criar, listar, buscar, remover e editar questões.
 */
public class TelaQuestoes extends JFrame {
	/**
	 * Controlador responsável por gerenciar Questoes VF e Questoes Alternativas.
	 */
    private QuestaoVFController questaoVFController;
    private QuestaoAlternativaController questaoAlternativaController;

    /**
     * Construtor da classe TelaQuestoes.
     * Inicializa os controllers das questões VF e questões de múltipla escolha.
     * Configura a janela da tela de questões.
     * Adiciona botões para criar, listar, buscar, remover e editar questões.
     */
    public TelaQuestoes() {
        questaoVFController = new QuestaoVFController();
        questaoAlternativaController = new QuestaoAlternativaController();

        // Configurações da janela
        setTitle("Questões");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal da janela
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        // Botão de Criar Questão VF
        JButton btnCriarQuestaoVF = new JButton("Criar Questão VF");
        btnCriarQuestaoVF.addActionListener(e -> criarQuestaoVF());
        panel.add(btnCriarQuestaoVF);

        // Botão de Criar Questão Alternativa
        JButton btnCriarQuestaoAlternativa = new JButton("Criar Questão Alternativa");
        btnCriarQuestaoAlternativa.addActionListener(e -> criarQuestaoAlternativa());
        panel.add(btnCriarQuestaoAlternativa);
        
        // Botão de Lista Questões
        JButton btnListaQuestoes = new JButton("Lista Questões");
        btnListaQuestoes.addActionListener(e -> listarQuestoes());
        panel.add(btnListaQuestoes);
        
        // Botão de Listar Questões por Matéria
        JButton btnListarPorMateria = new JButton("Listar Questões por Matéria");
        btnListarPorMateria.addActionListener(e -> listarQuestoesPorMateria());
        panel.add(btnListarPorMateria);
        
        // Botão de Buscar Questão
        JButton btnBuscarQuestao = new JButton("Buscar Questão");
        btnBuscarQuestao.addActionListener(e -> buscarQuestao());
        panel.add(btnBuscarQuestao);

        // Botão de Remover Questão
        JButton btnRemoverQuestao = new JButton("Remover Questão");
        btnRemoverQuestao.addActionListener(e -> removerQuestao());
        panel.add(btnRemoverQuestao);
        
        // Botão de Editar Questão
        JButton btnEditarQuestao = new JButton("Editar Questão");
        btnEditarQuestao.addActionListener(e -> editarQuestao());
        panel.add(btnEditarQuestao);


    }
    
    /**
     * Cria uma nova Questão VF por meio de uma janela de diálogo.
     * A janela permite ao usuário inserir o enunciado, matéria e resposta da questão.
     * A questão é então criada e adicionada ao QuestaoVFController.
     */
    public void criarQuestaoVF() {
        // Criação da janela de diálogo para criar uma nova questão VF
        JDialog dialog = new JDialog(this, "Criar Questão VF", true);
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Painel principal da janela de diálogo
        JPanel panel = new JPanel();
        dialog.add(panel);

        // Campos de texto para o enunciado, matéria e resposta
        JLabel lblEnunciado = new JLabel("Enunciado:");
        JTextField txtEnunciado = new JTextField(30);

        JLabel lblMateria = new JLabel("Matéria:");
        JTextField txtMateria = new JTextField(30);

        JLabel lblResposta = new JLabel("Resposta (Verdadeira ou Falsa):");
        JTextField txtResposta = new JTextField(30);

        panel.add(lblEnunciado);
        panel.add(txtEnunciado);
        panel.add(lblMateria);
        panel.add(txtMateria);
        panel.add(lblResposta);
        panel.add(txtResposta);

        // Botão de OK para criar a questão VF
        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(e -> {
            // Obtém os valores dos campos de texto
            String enunciado = txtEnunciado.getText();
            String materia = txtMateria.getText();
            String respostaStr = txtResposta.getText();

            boolean resposta;
            // Verifica se a resposta é "Verdadeira" ou "Falsa"
            if (respostaStr.equalsIgnoreCase("verdadeira")) {
                resposta = true;
            } else if (respostaStr.equalsIgnoreCase("falsa")) {
                resposta = false;
            } else {
                // Validação: exibe uma mensagem de erro se a resposta não for válida
                JOptionPane.showMessageDialog(null, "Resposta inválida. Digite Verdadeira ou Falsa.", "Erro", JOptionPane.ERROR_MESSAGE);
                return; // Sai do método se a resposta for inválida
            }

            // Obter o número da próxima questão
            int numQuestao = questaoVFController.getProximoNumQuestao();

            // Criar a questão VF e adicioná-la ao QuestaoVFController
            QuestaoVF questaoVF = new QuestaoVF(enunciado, materia, numQuestao, resposta);
            questaoVFController.adicionarQuestaoVF(questaoVF);

            // Exibir mensagem de sucesso e fechar a janela de diálogo
            JOptionPane.showMessageDialog(null, "Questão VF criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose(); // Fecha a janela de diálogo após criar a questão
        });

        panel.add(btnOK);

        // Exibição da janela de diálogo
        dialog.setVisible(true);
    }


    /**
     * Cria uma nova Questão Alternativa por meio de uma janela de diálogo.
     * A janela permite ao usuário inserir a matéria, enunciado, alternativas e resposta correta da questão.
     * A questão é então criada e adicionada ao QuestaoAlternativaController.
     */
    public void criarQuestaoAlternativa() {
        // Criação da janela de diálogo para criar uma nova questão alternativa
        JDialog dialog = new JDialog(this, "Criar Questão Alternativa", true);
        dialog.setSize(400, 400);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Painel principal da janela de diálogo
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        dialog.add(panel);

        // Campos de texto para a matéria, enunciado, alternativas e resposta
        JLabel lblMateria = new JLabel("Matéria:");
        JTextField txtMateria = new JTextField(30);

        JLabel lblEnunciado = new JLabel("Enunciado:");
        JTextField txtEnunciado = new JTextField(30);

        JLabel lblAlternativas = new JLabel("Alternativas:");

        List<JPanel> alternativasPanelList = new ArrayList<>();

        // Criação das alternativas
        for (int i = 0; i < 4; i++) {
            JPanel alternativaPanel = new JPanel();
            JTextField txtAlternativa = new JTextField(15);

            alternativaPanel.add(new JLabel((char) ('A' + i) + ")"));
            alternativaPanel.add(txtAlternativa);

            alternativasPanelList.add(alternativaPanel);
        }

        JLabel lblResposta = new JLabel("Resposta (A, B, C, ...):");
        JTextField txtResposta = new JTextField(5);

        panel.add(lblMateria);
        panel.add(txtMateria);
        panel.add(lblEnunciado);
        panel.add(txtEnunciado);
        panel.add(lblAlternativas);

        // Adiciona as alternativas ao painel
        for (JPanel alternativaPanel : alternativasPanelList) {
            panel.add(alternativaPanel);
        }

        panel.add(lblResposta);
        panel.add(txtResposta);

        // Botão de OK para criar a questão alternativa
        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(e -> {
            // Obtém os valores dos campos de texto
            String materia = txtMateria.getText();
            String enunciado = txtEnunciado.getText();
            String[] alternativas = new String[4];
            char resposta = ' ';

            // Obtém as alternativas e a resposta correta
            for (int i = 0; i < 4; i++) {
                JPanel alternativaPanel = alternativasPanelList.get(i);
                JTextField txtAlternativa = (JTextField) alternativaPanel.getComponent(1);
                String alternativa = txtAlternativa.getText();

                // Validação: verifica se todas as alternativas foram preenchidas
                if (alternativa.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todas as alternativas.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                alternativas[i] = alternativa;

                // Verifica se a resposta corresponde a uma das alternativas
                if (txtResposta.getText().equalsIgnoreCase(String.valueOf((char) ('A' + i)))) {
                    resposta = (char) ('A' + i);
                }
            }

            // Validação: verifica se uma resposta válida foi selecionada
            if (resposta == ' ') {
                JOptionPane.showMessageDialog(null, "Resposta inválida. Selecione uma das alternativas como resposta correta.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obter o número da próxima questão
            int numQuestao = questaoAlternativaController.getProximoNumQuestao();

            // Criar a questão Alternativa e adicioná-la ao QuestaoAlternativaController
            QuestaoAlternativa questaoAlternativa = new QuestaoAlternativa(enunciado, materia, numQuestao, alternativas, resposta);
            questaoAlternativaController.adicionarQuestaoAlternativa(questaoAlternativa);

            // Exibir mensagem de sucesso e fechar a janela de diálogo
            JOptionPane.showMessageDialog(null, "Questão Alternativa criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        });

        panel.add(btnOK);

        // Exibição da janela de diálogo
        dialog.setVisible(true);
    }


    /**
     * Retorna uma representação em formato de string da questão fornecida.
     *
     * @param questao A questão a ser exibida.
     * @return Uma representação em formato de string da questão.
     */
    public String exibirQuestao(Questao questao) {
        // Cria uma string com o número da questão
        String numQuestaoStr = "Número da Questão: " + questao.getNumQuestao() + "\n";

        if (questao instanceof QuestaoVF) {
            // Verifica se a questão é do tipo Verdadeiro ou Falso
            QuestaoVF questaoVF = (QuestaoVF) questao;
            return numQuestaoStr +
                    "Matéria: " + questaoVF.getMateria() + "\n" +
                    "Enunciado: " + questaoVF.getQuestao() + "\n" +
                    "Resposta: " + (questaoVF.getRespostaCorreta() ? "Verdadeira" : "Falsa");
        } else if (questao instanceof QuestaoAlternativa) {
            // Verifica se a questão é do tipo Alternativa
            QuestaoAlternativa questaoAlternativa = (QuestaoAlternativa) questao;
            StringBuilder sb = new StringBuilder();
            sb.append(numQuestaoStr);
            sb.append("Matéria: ").append(questaoAlternativa.getMateria()).append("\n");
            sb.append("Enunciado: ").append(questaoAlternativa.getQuestao()).append("\n");
            for (int i = 0; i < questaoAlternativa.getAlternativas().length; i++) {
                // Percorre as alternativas da questão e as adiciona à string
                sb.append((char) ('A' + i)).append(") ").append(questaoAlternativa.getAlternativas()[i]).append("\n");
            }
            sb.append("Resposta: ").append(String.valueOf(questaoAlternativa.getResposta()));
            return sb.toString();
        } else {
            return ""; // Retorna uma string vazia se a instância da questão não for reconhecida
        }
    }


    /**
     * Lista todas as questões, exibindo-as em uma caixa de diálogo.
     */
    public void listarQuestoes() {
        // Obtém a lista de questões Verdadeiro ou Falso e de questões Alternativa
        List<QuestaoVF> questoesVF = questaoVFController.getQuestoesVF();
        List<QuestaoAlternativa> questoesAlternativa = questaoAlternativaController.getQuestoesAlternativa();

        // Cria uma lista para armazenar todas as questões
        List<Questao> todasQuestoes = new ArrayList<>();

        // Adiciona todas as questões VF à lista
        todasQuestoes.addAll(questoesVF);

        // Adiciona todas as questões Alternativa à lista
        todasQuestoes.addAll(questoesAlternativa);

        // Ordena a lista de questões pelo número da questão, utilizando um Comparator personalizado
        Collections.sort(todasQuestoes, new Comparator<Questao>() {
            @Override
            public int compare(Questao q1, Questao q2) {
                return Integer.compare(q1.getNumQuestao(), q2.getNumQuestao());
            }
        });

        // Utilizado para construir a string com as questões
        StringBuilder sb = new StringBuilder();

        // Percorre todas as questões e adiciona à string
        for (Questao questao : todasQuestoes) {
            sb.append(exibirQuestao(questao)).append("\n\n");
        }

        // Exibe a lista de questões em uma caixa de diálogo
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Questões", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Lista todas as questões de acordo com uma matéria específica, exibindo-as em uma caixa de diálogo.
     * A matéria é obtida através de uma entrada do usuário.
     */
    public void listarQuestoesPorMateria() {
        // Solicita ao usuário que digite a matéria
        String materia = JOptionPane.showInputDialog(null, "Digite a matéria:");

        // Validação de entrada de matéria vazia ou cancelada pelo usuário
        if (materia == null || materia.trim().isEmpty()) {
            return;
        }

        // Obtém a lista de questões Verdadeiro ou Falso e de questões Alternativa
        List<QuestaoVF> questoesVF = questaoVFController.getQuestoesVF();
        List<QuestaoAlternativa> questoesAlternativa = questaoAlternativaController.getQuestoesAlternativa();

        // Utilizado para construir a string com as questões encontradas
        StringBuilder sb = new StringBuilder();

        // Filtra as questões por matéria e adiciona à string
        for (QuestaoVF questaoVF : questoesVF) {
            if (questaoVF.getMateria().equalsIgnoreCase(materia)) {
                sb.append(exibirQuestao(questaoVF)).append("\n\n");
            }
        }

        for (QuestaoAlternativa questaoAlternativa : questoesAlternativa) {
            if (questaoAlternativa.getMateria().equalsIgnoreCase(materia)) {
                sb.append(exibirQuestao(questaoAlternativa)).append("\n\n");
            }
        }

        // Verifica se foram encontradas questões com a matéria informada
        if (sb.length() > 0) {
            // Exibe a lista de questões encontradas em uma caixa de diálogo
            JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Questões por Matéria", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Exibe uma mensagem informando que não foram encontradas questões com a matéria informada
            JOptionPane.showMessageDialog(null, "Não foram encontradas questões para a matéria informada.", "Lista de Questões por Matéria", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    

    /**
     * Abre uma janela de diálogo para buscar uma questão. O usuário pode selecionar o tipo de questão
     * (Questão Alternativa ou Questão VF) e digitar o número da questão desejada.
     */
    public void buscarQuestao() {
        // Criação da janela de diálogo
        JDialog dialog = new JDialog(this, "Buscar Questão", true);
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Painel principal da janela de diálogo
        JPanel panel = new JPanel();
        dialog.add(panel);

        // Botões para seleção do tipo de questão
        JButton btnQuestaoAlternativa = new JButton("Questão Alternativa");
        JButton btnQuestaoVF = new JButton("Questão VF");

        // Campo de texto para digitar o número da questão
        JLabel lblNumeroQuestao = new JLabel("Número da Questão:");
        JTextField txtNumeroQuestao = new JTextField(10);

        panel.add(btnQuestaoAlternativa);
        panel.add(btnQuestaoVF);
        panel.add(lblNumeroQuestao);
        panel.add(txtNumeroQuestao);

        // Ação do botão "Questão Alternativa"
        btnQuestaoAlternativa.addActionListener(e -> {
            // Obtém o número da questão digitado
            int numQuestao = Integer.parseInt(txtNumeroQuestao.getText());

            // Busca a questão de múltipla escolha pelo número
            QuestaoAlternativa questaoAlternativa = questaoAlternativaController.buscarQuestaoAlternativa(numQuestao);

            if (questaoAlternativa != null) {
                // Exibe a questão de múltipla escolha encontrada em uma caixa de diálogo
                JOptionPane.showMessageDialog(null, exibirQuestao(questaoAlternativa), "Questão Alternativa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Exibe uma mensagem de erro se a questão de múltipla escolha não for encontrada
                JOptionPane.showMessageDialog(null, "Questão Alternativa não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); // Fecha a janela de diálogo após buscar a questão
        });

        // Ação do botão "Questão VF"
        btnQuestaoVF.addActionListener(e -> {
            // Obtém o número da questão digitado
            int numQuestao = Integer.parseInt(txtNumeroQuestao.getText());

            // Busca a questão Verdadeiro ou Falso pelo número
            QuestaoVF questaoVF = questaoVFController.buscarQuestaoVF(numQuestao);

            if (questaoVF != null) {
                // Exibe a questão Verdadeiro ou Falso encontrada em uma caixa de diálogo
                JOptionPane.showMessageDialog(null, exibirQuestao(questaoVF), "Questão VF", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Exibe uma mensagem de erro se a questão Verdadeiro ou Falso não for encontrada
                JOptionPane.showMessageDialog(null, "Questão VF não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); // Fecha a janela de diálogo após buscar a questão
        });

        // Exibição da janela de diálogo:
        dialog.setVisible(true);
    }


    /**
     * Exibe uma janela de confirmação para exclusão de uma questão. Se o usuário confirmar a exclusão,
     * a questão é removida do sistema.
     *
     * @param questao A questão a ser excluída.
     */
    public void mostrarConfirmacaoExclusao(Questao questao) {
        // Exibe uma caixa de diálogo de confirmação para a exclusão da questão
        int opcao = JOptionPane.showOptionDialog(
            null,
            "Deseja realmente excluir a questão?",
            "Confirmação de Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new Object[]{"OK", "Cancelar"},
            "OK"
        );

        // Verifica se a opção selecionada foi "OK" (excluir a questão)
        if (opcao == JOptionPane.YES_OPTION) {
            // Verifica o tipo da questão
            if (questao instanceof QuestaoAlternativa) {
                QuestaoAlternativa questaoAlternativa = (QuestaoAlternativa) questao;

                // Chama o controlador para remover a questão de múltipla escolha
                questaoAlternativaController.removerQuestaoAlternativa(questaoAlternativa);
            } else if (questao instanceof QuestaoVF) {
                QuestaoVF questaoVF = (QuestaoVF) questao;

                // Chama o controlador para remover a questão Verdadeiro ou Falso
                questaoVFController.removerQuestaoVF(questaoVF);
            }

            // Exibe uma mensagem de sucesso após a exclusão da questão
            JOptionPane.showMessageDialog(null, "Questão removida com sucesso.");

            // Atualiza a lista de questões exibida na tela
            listarQuestoes();
        }
    }


    
    
    /**
     * Abre uma janela de diálogo para remover uma questão do sistema. O usuário pode selecionar
     * entre os tipos de questão (Questão Alternativa ou Questão VF) e informar o número da questão
     * a ser removida.
     */
    public void removerQuestao() {
        // Criação da janela de diálogo para remover uma questão
        JDialog dialog = new JDialog(this, "Remover Questão", true);
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Painel principal da janela de diálogo
        JPanel panel = new JPanel();
        dialog.add(panel);

        // Rótulo e campo de texto para digitar o número da questão
        JLabel lblNumeroQuestao = new JLabel("Número da Questão:");
        JTextField txtNumeroQuestao = new JTextField(10);

        // Adiciona o rótulo e o campo de texto ao painel
        panel.add(lblNumeroQuestao);
        panel.add(txtNumeroQuestao);

        // Botões para selecionar uma QuestaoAlternativa ou QuestaoVF
        JButton btnQuestaoAlternativa = new JButton("Questão Alternativa");
        JButton btnQuestaoVF = new JButton("Questão VF");

        // Adiciona os botões ao painel
        panel.add(btnQuestaoAlternativa);
        panel.add(btnQuestaoVF);

        // Ação do botão "Questão Alternativa"
        btnQuestaoAlternativa.addActionListener(e -> {
            // Obtém o número da questão digitado no campo de texto
            int numQuestao = Integer.parseInt(txtNumeroQuestao.getText());
            // Busca a questão Alternativa com base no número da questão
            QuestaoAlternativa questaoAlternativa = questaoAlternativaController.buscarQuestaoAlternativa(numQuestao);

            if (questaoAlternativa != null) {
                // Mostra uma confirmação de exclusão da questão
                mostrarConfirmacaoExclusao(questaoAlternativa);
            } else {
                // Exibe uma mensagem de erro se a questão Alternativa não for encontrada
                JOptionPane.showMessageDialog(null, "Questão Alternativa não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); // Fecha a janela de diálogo após buscar a questão
        });

        // Ação do botão "Questão VF"
        btnQuestaoVF.addActionListener(e -> {
            // Obtém o número da questão digitado no campo de texto
            int numQuestao = Integer.parseInt(txtNumeroQuestao.getText());
            // Busca a questão VF com base no número da questão
            QuestaoVF questaoVF = questaoVFController.buscarQuestaoVF(numQuestao);

            if (questaoVF != null) {
                // Mostra uma confirmação de exclusão da questão
                mostrarConfirmacaoExclusao(questaoVF);
            } else {
                // Exibe uma mensagem de erro se a questão VF não for encontrada
                JOptionPane.showMessageDialog(null, "Questão VF não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); // Fecha a janela de diálogo após buscar a questão
        });

        // Exibição da janela de diálogo
        dialog.setVisible(true);
    }

    
    /**
     * Abre uma janela de diálogo para editar uma questão do sistema. O usuário pode selecionar
     * entre os tipos de questão (Questão VF ou Questão Alternativa) e informar o número da questão
     * a ser editada.
     */
    public void editarQuestao() {
        // Criação da janela de diálogo para editar uma questão
        JDialog dialog = new JDialog(this, "Editar Questão", true);
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Painel principal da janela de diálogo
        JPanel panel = new JPanel();
        dialog.add(panel);

        // Rótulo e campo de texto para digitar o número da questão
        JLabel lblNumeroQuestao = new JLabel("Número da Questão:");
        JTextField txtNumeroQuestao = new JTextField(10);

        // Adiciona o rótulo e o campo de texto ao painel
        panel.add(lblNumeroQuestao);
        panel.add(txtNumeroQuestao);

        // Botão para selecionar uma QuestaoVF
        JButton btnQuestaoVF = new JButton("QuestaoVF");
        btnQuestaoVF.addActionListener(e -> {
            // Obtém o número da questão digitado no campo de texto
            int numeroQuestao = Integer.parseInt(txtNumeroQuestao.getText());

            // Verifica se a questão VF existe
            if (!questaoVFController.existeQuestao(numeroQuestao)) {
                // Exibe uma mensagem de erro se a questão VF não for encontrada
                JOptionPane.showMessageDialog(dialog, "Questão VF não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtém a questão VF com base no número da questão
            QuestaoVF questaoVF = questaoVFController.buscarQuestaoVF(numeroQuestao);
            // Chama o método para editar a questão VF
            editarQuestaoVF(questaoVF);

            // Fecha a janela de diálogo
            dialog.dispose();
        });

        // Botão para selecionar uma QuestaoAlternativa
        JButton btnQuestaoAlternativa = new JButton("QuestaoAlternativa");
        btnQuestaoAlternativa.addActionListener(e -> {
            // Obtém o número da questão digitado no campo de texto
            int numeroQuestao = Integer.parseInt(txtNumeroQuestao.getText());

            // Verifica se a questão Alternativa existe
            if (!questaoAlternativaController.existeQuestao(numeroQuestao)) {
                // Exibe uma mensagem de erro se a questão Alternativa não for encontrada
                JOptionPane.showMessageDialog(dialog, "Questão Alternativa não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtém a questão Alternativa com base no número da questão
            QuestaoAlternativa questaoAlternativa = questaoAlternativaController.buscarQuestaoAlternativa(numeroQuestao);
            // Chama o método para editar a questão Alternativa
            editarQuestaoAlternativa(questaoAlternativa);

            // Fecha a janela de diálogo
            dialog.dispose();
        });

        // Adiciona os botões ao painel
        panel.add(btnQuestaoVF);
        panel.add(btnQuestaoAlternativa);

        // Exibe a janela de diálogo
        dialog.setVisible(true);
    }



    
    /**
     * Abre uma janela de diálogo para editar uma questão do tipo Verdadeiro ou Falso (VF).
     * Os campos de enunciado e resposta correta são preenchidos com os valores atuais da questãoVF
     * fornecida como parâmetro. O usuário pode fazer as alterações desejadas e salvar a questão atualizada.
     *
     * @param questaoVF A questão VF a ser editada.
     */
    public void editarQuestaoVF(QuestaoVF questaoVF) {
        // Cria uma janela de diálogo para editar a questão Verdadeiro ou Falso
        JDialog dialog = new JDialog();
        dialog.setTitle("Editar Questão Verdadeiro ou Falso");
        dialog.setModal(true);
        dialog.setSize(400, 300);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        // Cria um rótulo para o campo de enunciado
        JLabel lblEnunciado = new JLabel("Enunciado:");
        // Cria um campo de texto para o enunciado da questão e preenche-o com o valor atual da questão
        JTextField txtEnunciado = new JTextField(questaoVF.getQuestao());
        // Adiciona o rótulo e o campo de texto ao painel
        panel.add(lblEnunciado);
        panel.add(txtEnunciado);

        // Cria um rótulo para a resposta correta
        JLabel lblResposta = new JLabel("Resposta (V ou F):");
        // Cria um campo de texto para a resposta correta e preenche-o com o valor atual da resposta
        JTextField txtResposta = new JTextField(String.valueOf(questaoVF.getRespostaCorreta()));
        // Adiciona o rótulo e o campo de texto ao painel
        panel.add(lblResposta);
        panel.add(txtResposta);

        // Cria um botão para salvar as alterações na questão
        JButton btnSalvar = new JButton("Salvar");
        // Adiciona um ouvinte de ação ao botão
        btnSalvar.addActionListener(e -> {
            // Atualiza os valores da questão Verdadeiro ou Falso com base nos campos preenchidos
            questaoVF.setQuestao(txtEnunciado.getText());
            // Define a resposta correta com base no valor do campo de texto convertido para maiúsculo
            questaoVF.setRespostaCorreta(txtResposta.getText().equalsIgnoreCase("V"));

            // Chama o controlador para atualizar a questão no sistema
            questaoVFController.atualizarQuestao(questaoVF);

            // Exibe uma mensagem de sucesso ao usuário
            JOptionPane.showMessageDialog(dialog, "Questão VF atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Fecha a janela de diálogo
            dialog.dispose();
        });

        // Adiciona o botão ao painel principal
        panel.add(btnSalvar);

        // Adiciona o painel ao diálogo
        dialog.add(panel);
        // Torna o diálogo visível
        dialog.setVisible(true);
    }


    
    /**
     * Abre uma janela de diálogo para editar uma questão do tipo Alternativa (múltipla escolha).
     * Os campos de enunciado, opções e resposta correta são preenchidos com os valores atuais da questaoAlternativa
     * fornecida como parâmetro. O usuário pode fazer as alterações desejadas e salvar a questão atualizada.
     *
     * @param questaoAlternativa A questão Alternativa a ser editada.
     */
    public void editarQuestaoAlternativa(QuestaoAlternativa questaoAlternativa) {
        // Cria uma janela de diálogo para editar a questão de múltipla escolha
        JDialog dialog = new JDialog();
        dialog.setTitle("Editar Questão de Múltipla Escolha");
        dialog.setModal(true);
        dialog.setSize(700, 600);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        // Cria um rótulo para o campo de enunciado
        JLabel lblEnunciado = new JLabel("Enunciado:");
        // Cria um campo de texto para o enunciado da questão e preenche-o com o valor atual da questão
        JTextField txtEnunciado = new JTextField(questaoAlternativa.getQuestao());
        // Adiciona o rótulo e o campo de texto ao painel
        panel.add(lblEnunciado);
        panel.add(txtEnunciado);

        // Cria um rótulo para as opções da questão
        JLabel lblOpcoes = new JLabel("Escolha a Opção a ser editada");
        panel.add(lblOpcoes);

        // Cria um modelo de lista para armazenar as opções da questão
        DefaultListModel<String> listModel = new DefaultListModel<>();
        // Preenche o modelo de lista com as opções atuais da questão
        for (String opcao : questaoAlternativa.getAlternativas()) {
            listModel.addElement(opcao);
        }
        // Cria uma lista visual para exibir as opções usando o modelo de lista
        JList<String> listOpcoes = new JList<>(listModel);
        // Cria um painel de rolagem para a lista de opções
        JScrollPane scrollPane = new JScrollPane(listOpcoes);
        // Adiciona o painel de rolagem ao painel principal
        panel.add(scrollPane);

        // Cria um botão para editar a opção selecionada
        JButton btnEditarOpcao = new JButton("Editar Opção");
        // Adiciona um ouvinte de ação ao botão
        btnEditarOpcao.addActionListener(e -> {
            // Obtém o índice da opção selecionada na lista
            int selectedIndex = listOpcoes.getSelectedIndex();
            // Verifica se uma opção está selecionada
            if (selectedIndex != -1) {
                // Obtém a opção atualmente selecionada
                String opcaoAtual = listModel.getElementAt(selectedIndex);
                // Exibe uma caixa de diálogo para o usuário inserir a nova opção
                String novaOpcao = JOptionPane.showInputDialog(dialog, "Digite a nova opção:", opcaoAtual);
                // Verifica se o usuário inseriu uma nova opção e se ela não está vazia
                if (novaOpcao != null && !novaOpcao.isEmpty()) {
                    // Atualiza a opção no modelo de lista
                    listModel.setElementAt(novaOpcao, selectedIndex);
                }
            }
        });
        // Adiciona o botão ao painel principal
        panel.add(btnEditarOpcao);

        // Cria um rótulo para a resposta correta
        JLabel lblRespostaCorreta = new JLabel("Resposta Correta:");
        // Cria um campo de texto para a resposta correta e preenche-o com o valor atual da resposta
        JTextField txtRespostaCorreta = new JTextField(String.valueOf(questaoAlternativa.getResposta()));
        // Adiciona o rótulo e o campo de texto ao painel principal
        panel.add(lblRespostaCorreta);
        panel.add(txtRespostaCorreta);

        // Cria um botão para salvar as alterações na questão
        JButton btnSalvar = new JButton("Salvar");
        // Adiciona um ouvinte de ação ao botão
        btnSalvar.addActionListener(e -> {
            // Atualiza os valores da questão de múltipla escolha com base nos campos preenchidos
            questaoAlternativa.setQuestao(txtEnunciado.getText());

            List<String> opcoes = new ArrayList<>();
            // Percorre o modelo de lista e adiciona cada elemento à lista de opções
            for (int i = 0; i < listModel.getSize(); i++) {
                opcoes.add(listModel.getElementAt(i));
            }
            questaoAlternativa.setAlternativas(opcoes);

            String respostaCorreta = txtRespostaCorreta.getText();
            // Verifica se a resposta correta não está vazia
            if (!respostaCorreta.isEmpty()) {
                // Define a resposta correta como o primeiro caractere do campo de texto
                questaoAlternativa.setResposta(respostaCorreta.charAt(0));
            }

            // Chama o controlador para atualizar a questão no sistema
            questaoAlternativaController.atualizarQuestao(questaoAlternativa);

            // Exibe uma mensagem de sucesso ao usuário
            JOptionPane.showMessageDialog(dialog, "Questão de Múltipla Escolha atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Fecha a janela de diálogo
            dialog.dispose();
        });
        // Adiciona o botão ao painel principal
        panel.add(btnSalvar);

        // Adiciona o painel ao diálogo
        dialog.add(panel);
        // Torna o diálogo visível
        dialog.setVisible(true);
    }
}