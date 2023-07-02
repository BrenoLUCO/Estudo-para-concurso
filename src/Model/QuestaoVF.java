/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package Model;

/**
 * Classe que representa uma questão do tipo Verdadeiro ou Falso.
 *
 * Essa classe herda os atributos e métodos da classe abstrata Questao e adiciona um atributo
 * para armazenar a resposta correta da questão.
 */
public class QuestaoVF extends Questao {
    private boolean respostaCorreta; // Indica se a resposta correta é verdadeira (true) ou falsa (false)

    /**
     * Construtor da classe QuestaoVF.
     *
     * @param questao         O texto da questão.
     * @param materia         A matéria à qual a questão pertence.
     * @param numQuestao      O número da questão.
     * @param respostaCorreta A resposta correta da questão (true para verdadeira, false para falsa).
     */
    public QuestaoVF(String questao, String materia, int numQuestao, boolean respostaCorreta) {
        super(questao, materia, numQuestao);
        this.respostaCorreta = respostaCorreta;
    }
    
    //Metodos especiais
    public boolean getRespostaCorreta() {
        return respostaCorreta;
    }
    
    public void setRespostaCorreta(boolean respostaCorreta) {
		this.respostaCorreta = respostaCorreta;
	}

    public String getQuestao() {
        return super.questao;
    }
    
    public void setQuestao(String questao) {
        super.questao = questao;
    }

    public String getMateria() {
        return super.materia;
    }
    
    public int getNumQuestao() {
    	return super.numQuestao;
    }
}