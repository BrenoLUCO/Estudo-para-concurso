/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package Model;

/**
 * Classe abstrata que representa uma questão.
 * 
 * Essa classe contém os atributos que armazenam os dados de uma questão e métodos
 * de acesso para manipular esses dados. A classe é abstrata, pois não pode ser instanciada
 * diretamente, sendo necessária a implementação de subclasses (QuestaoAlternativa e QuestaoVF) 
 * para criar tipos específicos de questões.
 */
public abstract class Questao {
    // Atributos
    protected String questao; // Texto da questão
    protected String materia; // Matéria à qual a questão pertence
    protected int numQuestao; // Número da questão

    /**
     * Construtor da classe Questao.
     *
     * @param questao    O texto da questão.
     * @param materia    A matéria à qual a questão pertence.
     * @param numQuestao O número da questão.
     */
    public Questao(String questao, String materia, int numQuestao) {
        this.questao = questao;
        this.materia = materia;
        this.numQuestao = numQuestao;
    }
    // Métodos Especiais
    public String getQuestao() {
        return questao;
    }
    
    public void setQuestao(String questao) {
    	this.questao = questao;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getNumQuestao() {
        return numQuestao;
    }

    public void setNumQuestao(int numQuestao) {
        this.numQuestao = numQuestao;
    }

}