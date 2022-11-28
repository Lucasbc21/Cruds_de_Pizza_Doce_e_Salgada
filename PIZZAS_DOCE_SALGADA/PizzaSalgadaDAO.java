package edu.curso;

import java.util.List;

public interface PizzaSalgadaDAO {
	
	void criar(PizzaSalgada ps);
    List<PizzaSalgada> pesquisarPizzaSalgadaSabor(String sabor);
    void apagar(PizzaSalgada ps);
    void atualizar(String saborAntigo, PizzaSalgada ps);
    
}
