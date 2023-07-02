/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package Controller;

import Model.QuestaoVF;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsável por gerenciar as questões de verdadeiro ou falso.
 */
public class QuestaoVFController {
    private List<QuestaoVF> listaQuestoesVF;
    private int numQuestaoAtual; // Atributo para controlar o número da próxima questão

    /**
     * Construtor da classe QuestaoVFController.
     * Inicializa a lista de questões de verdadeiro ou falso e o número da próxima questão como 1.
     */
    public QuestaoVFController() {
        listaQuestoesVF = new ArrayList<>();
        numQuestaoAtual = 1; // Inicializa com o número 1
    }
    
    /**
     * Busca uma questão de verdadeiro ou falso pelo número da questão.
     *
     * @param numQuestao O número da questão a ser buscada.
     * @return A questão de verdadeiro ou falso encontrada, ou null caso não seja encontrada.
     */
    public QuestaoVF buscarQuestaoVF(int numQuestao) {
        for (QuestaoVF questao : listaQuestoesVF) {
            if (questao.getNumQuestao() == numQuestao) {
                return questao;
            }
        }
        return null; // Caso a questão não seja encontrada
    }

    /**
     * Adiciona uma questão de verdadeiro ou falso à lista de questões.
     * Atribui o número da questão e incrementa o número da próxima questão.
     *
     * @param questaoVF A questão de verdadeiro ou falso a ser adicionada.
     */
    public void adicionarQuestaoVF(QuestaoVF questaoVF) {
        questaoVF.setNumQuestao(numQuestaoAtual); // Atribui o número da questão
        listaQuestoesVF.add(questaoVF);
        numQuestaoAtual++; // Incrementa o número da próxima questão
    }

    /**
     * Remove uma questão de verdadeiro ou falso da lista de questões.
     *
     * @param questaoVF A questão de verdadeiro ou falso a ser removida.
     */
    public void removerQuestaoVF(QuestaoVF questaoVF) {
        listaQuestoesVF.remove(questaoVF);
    }

    /**
     * Atualiza uma questão de verdadeiro ou falso na lista de questões.
     *
     * @param questaoVF A questão de verdadeiro ou falso atualizada.
     */
    public void atualizarQuestao(QuestaoVF questaoVF) {
        for (int i = 0; i < listaQuestoesVF.size(); i++) {
            QuestaoVF questao = listaQuestoesVF.get(i);
            if (questao.getNumQuestao() == questaoVF.getNumQuestao()) {
                listaQuestoesVF.set(i, questaoVF);
                break;
            }
        }
    }
    
    /**
     * Verifica se uma questão de verdadeiro ou falso existe na lista de questões.
     *
     * @param numQuestao O número da questão a ser verificada.
     * @return true se a questão existir, false caso contrário.
     */
    public boolean existeQuestao(int numQuestao) {
        for (QuestaoVF questao : listaQuestoesVF) {
            if (questao.getNumQuestao() == numQuestao) {
                return true;
            }
        }
        return false; // Caso a questão não seja encontrada
    }
    
    /**
     * Obtém a lista de todas as questões de verdadeiro ou falso.
     *
     * @return A lista de questões de verdadeiro ou falso.
     */
    public List<QuestaoVF> getQuestoesVF() {
        return listaQuestoesVF;
    }

    /**
     * Obtém o número da próxima questão a ser adicionada.
     *
     * @return O número da próxima questão.
     */
    public int getProximoNumQuestao() {
        return numQuestaoAtual;
    }

}