/**
* @author Breno Lucena
* @author Breno Fernandes
* @since 2023
* @version 1.0
*/
package Controller;

import Model.Edital;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsável por gerenciar os editais.
 */
public class EditalController {
	/**
	 * 	lista de editais responsável por gerenciar os editais.
	 */
    private static List<Edital> listaEditais;

    /**
     * Construtor da classe EditalController.
     * Inicializa a lista de editais.
     */
    public EditalController() {
        listaEditais = new ArrayList<>();
    }

    /**
     * Adiciona um edital à lista de editais.
     *
     * @param edital O edital a ser adicionado.
     */
    public void adicionarEdital(Edital edital) {
        listaEditais.add(edital);
    }

    /**
     * Remove um edital da lista de editais.
     *
     * @param edital O edital a ser removido.
     */
    public void removerEdital(Edital edital) {
        listaEditais.remove(edital);
    }

    /**
     * Retorna uma lista de editais abertos, ou seja, editais cujo período de inscrição está em andamento.
     *
     * @return A lista de editais abertos.
     */
    public List<Edital> getEditaisAbertos() {
        // Cria uma nova lista para armazenar os editais abertos
        List<Edital> editaisAbertos = new ArrayList<>();

        // Obtém a data atual
        LocalDate dataAtual = LocalDate.now();

        // Percorre a lista de editais
        for (Edital edital : listaEditais) {
            // Converte as datas de início e fim de inscrição do edital para o tipo LocalDate
            LocalDate dataInicio = LocalDate.parse(edital.getDataInicioInscricao());
            LocalDate dataFim = LocalDate.parse(edital.getDataFimInscricao());

            // Verifica se a data atual está entre a data de início e a data de fim de inscrição do edital
            if (dataAtual.isAfter(dataInicio) && dataAtual.isBefore(dataFim)) {
                // Adiciona o edital à lista de editais abertos
                editaisAbertos.add(edital);
            }
        }

        // Retorna a lista de editais abertos
        return editaisAbertos;
    }


    /**
     * Busca um edital pelo nome.
     *
     * @param nomeEdital O nome do edital a ser buscado.
     * @return O edital encontrado, ou null caso não seja encontrado.
     */
    public Edital buscarEditalPorNome(String nomeEdital) {
        // Percorre a lista de editais
        for (Edital edital : listaEditais) {
            // Verifica se o nome do edital é igual ao nome fornecido (ignorando maiúsculas e minúsculas)
            if (edital.getNome().equalsIgnoreCase(nomeEdital)) {
                // Retorna o edital encontrado
                return edital;
            }
        }

        // Caso nenhum edital seja encontrado, retorna null
        return null; 
    }

    /**
     * Edita um edital com base no seu nome.
     *
     * @param nomeEdital O nome do edital a ser editado.
     * @param novoEdital O novo edital com as informações atualizadas.
     */
    public void editarEdital(String nomeEdital, Edital novoEdital) {
        Edital edital = buscarEditalPorNome(nomeEdital);
        if (edital != null) {
            // Atualiza as informações do edital com as novas informações
            edital.setNome(novoEdital.getNome());
            edital.setDataInicioInscricao(novoEdital.getDataInicioInscricao());
            edital.setDataFimInscricao(novoEdital.getDataFimInscricao());
            edital.setDataProva(novoEdital.getDataProva());
            edital.setValorInscricao(novoEdital.getValorInscricao());
            edital.setEscolaridade(novoEdital.getEscolaridade());
            edital.setNumVagas(novoEdital.getNumVagas());
        }
    }
    
    /**
     * Retorna a lista de editais.
     *
     * @return A lista de editais.
     */
    public List<Edital> getEditais() {
        return listaEditais;
    }
}