package edu.curso.Controller;

import java.util.List;
import edu.curso.DAO.PlanoDAO;
import edu.curso.Entidade.Plano;

public class PlanoController {

    private PlanoDAO dao = new PlanoDAO();

    public void salvar(Plano plano) throws Exception {
        if (plano.getNome() == null || plano.getNome().trim().isEmpty()) {
            throw new Exception("Nome é obrigatório.");
        }
        if (plano.getModalidade() == null || plano.getModalidade().trim().isEmpty()) {
            throw new Exception("Modalidade é obrigatória.");
        }
        if (plano.getDuracao() <= 0) {
            throw new Exception("Duração deve ser maior que zero.");
        }
        if (plano.getPreco() <= 0) {
            throw new Exception("Preço deve ser maior que zero.");
        }

        if (plano.getId() == 0) {
            dao.inserir(plano);
        } else {
            dao.atualizar(plano);
        }
    }

    public void deletar(int id) throws Exception {
        dao.deletar(id);
    }

    public List<Plano> listar() throws Exception {
        return dao.listar();
    }

    public Plano pesquisar(int id) throws Exception {
        return dao.pesquisar(id);
    }
}
