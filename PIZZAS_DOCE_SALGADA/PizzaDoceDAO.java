package edu.curso;

import java.util.List;

public interface PizzaDoceDAO {
	
	void criar(PizzaDoce pd);
	List<PizzaDoce> pesquisarPizzaporCobertura(String cobertura);
	void apagar(PizzaDoce pd);
	void atualizar(String coberturaAntiga, PizzaDoce pd);

}
