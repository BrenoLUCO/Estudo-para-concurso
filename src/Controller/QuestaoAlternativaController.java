/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package Controller;

import Model.QuestaoAlternativa;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsável por gerenciar as questões de múltipla escolha.
 */
public class QuestaoAlternativaController {
    private List<QuestaoAlternativa> listaQuestoesAlternativa;
    private int numQuestaoAtual; // Atributo para controlar o número da próxima questão

    /**
     * Construtor da classe QuestaoAlternativaController.
     * Inicializa a lista de questões de múltipla escolha e o número da próxima questão como 1.
     */
    public QuestaoAlternativaController() {
        listaQuestoesAlternativa = new ArrayList<>();
        numQuestaoAtual = 1; // Inicializa com o número 1
    }

    /**
     * Adiciona uma questão de múltipla escolha à lista de questões.
     * Atribui o número da questão e incrementa o número da próxima questão.
     *
     * @param questaoAlternativa A questão de múltipla escolha a ser adicionada.
     */
    public void adicionarQuestaoAlternativa(QuestaoAlternativa questaoAlternativa) {
        questaoAlternativa.setNumQuestao(numQuestaoAtual); // Atribui o número da questão
        listaQuestoesAlternativa.add(questaoAlternativa);
        numQuestaoAtual++; // Incrementa o número da próxima questão
    }

    /**
     * Remove uma questão de múltipla escolha da lista de questões.
     *
     * @param questaoAlternativa A questão de múltipla escolha a ser removida.
     */
    public void removerQuestaoAlternativa(QuestaoAlternativa questaoAlternativa) {
        listaQuestoesAlternativa.remove(questaoAlternativa);
    }

    /**
     * Busca uma questão de múltipla escolha pelo número da questão.
     *
     * @param numQuestao O número da questão a ser buscada.
     * @return A questão de múltipla escolha encontrada, ou null caso não seja encontrada.
     */
    public QuestaoAlternativa buscarQuestaoAlternativa(int numQuestao) {
        // Percorre a lista de questões alternativas
        for (QuestaoAlternativa questao : listaQuestoesAlternativa) {
            // Verifica se o número da questão da iteração atual é igual ao número da questão buscada
            if (questao.getNumQuestao() == numQuestao) {
                // Retorna a questão alternativa encontrada
                return questao;
            }
        }
        // Retorna null caso a questão não seja encontrada na lista
        return null;
    }


    /**
     * Atualiza uma questão de múltipla escolha na lista de questões.
     *
     * @param questaoAlternativa A questão de múltipla escolha atualizada.
     */
    public void atualizarQuestao(QuestaoAlternativa questaoAlternativa) {
        // Percorre a lista de questões alternativas
        for (int i = 0; i < listaQuestoesAlternativa.size(); i++) {
            // Obtém uma questão alternativa da lista pelo índice
            QuestaoAlternativa questao = listaQuestoesAlternativa.get(i);
            // Verifica se o número da questão da iteração atual é igual ao número da questão que será atualizada
            if (questao.getNumQuestao() == questaoAlternativa.getNumQuestao()) {
                // Atualiza a questão alternativa na lista com a nova questão alternativa fornecida
                listaQuestoesAlternativa.set(i, questaoAlternativa);
                // Encerra o loop, já que a questão foi atualizada com sucesso
                break;
            }
        }
    }


    /**
     * Verifica se uma questão de múltipla escolha existe na lista de questões.
     *
     * @param numQuestao O número da questão a ser verificada.
     * @return true se a questão existir, false caso contrário.
     */
    public boolean existeQuestao(int numQuestao) {
        for (QuestaoAlternativa questao : listaQuestoesAlternativa) {
            if (questao.getNumQuestao() == numQuestao) {
                return true;
            }
        }
        return false; // Caso a questão não seja encontrada
    }

    /**
     * Obtém a lista de todas as questões de múltipla escolha.
     *
     * @return A lista de questões de múltipla escolha.
     */
    public List<QuestaoAlternativa> getQuestoesAlternativa() {
        return listaQuestoesAlternativa;
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