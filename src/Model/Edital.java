/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package Model;

/**
 * Representa um edital de concurso.
 * 
 * Esta classe contém os atributos que armazenam os dados de um edital e métodos
 * de acesso para manipular esses dados.
 */
public class Edital {
    private String nome; // Nome do edital
    private String dataInicioInscricao; // Data de início das inscrições
    private String dataFimInscricao; // Data de fim das inscrições
    private String dataProva; // Data da prova
    private double valorInscricao; // Valor da inscrição
    private String escolaridade; // Escolaridade exigida
    private int numVagas; // Número de vagas

    /**
     * Construtor da classe Edital.
     *
     * @param nome               O nome do edital.
     * @param dataInicioInscricao A data de início das inscrições.
     * @param dataFimInscricao    A data de fim das inscrições.
     * @param dataProva           A data da prova.
     * @param valorInscricao      O valor da inscrição.
     * @param escolaridade        A escolaridade exigida.
     * @param numVagas            O número de vagas.
     */
    public Edital(String nome, String dataInicioInscricao, String dataFimInscricao, String dataProva, double valorInscricao, String escolaridade, int numVagas) {
        this.nome = nome;
        this.dataInicioInscricao = dataInicioInscricao;
        this.dataFimInscricao = dataFimInscricao;
        this.dataProva = dataProva;
        this.valorInscricao = valorInscricao;
        this.escolaridade = escolaridade;
        this.numVagas = numVagas;
    }

    /**
     * Retorna uma representação em formato de string dos dados do edital.
     *
     * @return Uma string com os dados do edital formatados.
     */
    public String toString() {
        return "Nome: " + nome + "\n" +
               "Data de Abertura de Inscrição: " + dataInicioInscricao + "\n" +
               "Data de Fim de Inscrição: " + dataFimInscricao + "\n" +
               "Data da Prova: " + dataProva + "\n" +
               "Valor da Inscrição: " + valorInscricao + "\n" +
               "Escolaridade: " + escolaridade + "\n" +
               "Número de Vagas: " + numVagas;
    }

    // Métodos de acesso (getters e setters) para os atributos

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataInicioInscricao() {
        return dataInicioInscricao;
    }

    public void setDataInicioInscricao(String dataInicioInscricao) {
        this.dataInicioInscricao = dataInicioInscricao;
    }

    public String getDataFimInscricao() {
        return dataFimInscricao;
    }

    public void setDataFimInscricao(String dataFimInscricao) {
        this.dataFimInscricao = dataFimInscricao;
    }

    public String getDataProva() {
        return dataProva;
    }

    public void setDataProva(String dataProva) {
        this.dataProva = dataProva;
    }

    public double getValorInscricao() {
        return valorInscricao;
    }

    public void setValorInscricao(double valorInscricao) {
        this.valorInscricao = valorInscricao;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public int getNumVagas() {
        return numVagas;
    }

    public void setNumVagas(int numVagas) {
        this.numVagas = numVagas;
    }
}