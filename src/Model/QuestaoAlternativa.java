/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package Model;

import java.util.List;

/**
 * Classe que representa uma questão de múltipla escolha.
 *
 * Essa classe herda os atributos e métodos da classe abstrata Questao e adiciona um array para armazenar
 * as alternativas da questão e um char para armazenar a resposta correta.
 */
public class QuestaoAlternativa extends Questao {
    private String[] alternativas; // Array de alternativas da questão
    private char resposta; // Resposta correta da questão (representada por um caractere)

    /**
     * Construtor da classe QuestaoAlternativa.
     *
     * @param questao       O texto da questão.
     * @param materia       A matéria à qual a questão pertence.
     * @param numQuestao    O número da questão.
     * @param alternativas  O array de alternativas da questão.
     * @param resposta      A resposta correta da questão (representada por um caractere).
     */
    public QuestaoAlternativa(String questao, String materia, int numQuestao, String[] alternativas, char resposta) {
        super(questao, materia, numQuestao);
        this.alternativas = alternativas;
        this.resposta = resposta;
    }
    
    public String[] getAlternativas() {
        return alternativas;
    }
    
    public void setAlternativas(List<String> opcoes) {
 		this.alternativas = opcoes.toArray(new String[0]);
 	}

    public char getResposta() {
        return resposta;
    }
    
    public void setResposta(char resposta) {
        this.resposta = resposta;
    }

    public String getMateria() {
        return super.materia;
    }
    
    public int getNumQuestao() {
    	return super.numQuestao;	
    }
    
    public void setQuestao(String questao) {
        super.questao = questao;
    }
}