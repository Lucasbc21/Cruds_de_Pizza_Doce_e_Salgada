package edu.curso;

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PizzaSalgadaControl {
	
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty sabor = new SimpleStringProperty("");
	private StringProperty borda = new SimpleStringProperty("");
	private DoubleProperty preço = new SimpleDoubleProperty(0.0);
	
	private PizzaSalgadaDAO pizzasalgadaDAO = new PizzaSalgadaDAOImplement();
	
	private boolean editando = false;
	private String saborAntigo = null;
	



	private ObservableList<PizzaSalgada> lista = 
			FXCollections.observableArrayList();
	
	
	public PizzaSalgada getentity() {
		
		PizzaSalgada ps = new PizzaSalgada();
		
		ps.setId(id.get());
		ps.setSabor(sabor.get());
		ps.setBorda(borda.get());
		ps.setPreço(preço.get());
		return ps;
	}
	
	public void setEntity(PizzaSalgada ps) {
		
		id.set(ps.getId());
		sabor.set(ps.getSabor());
		borda.set(ps.getBorda());
		preço.set(ps.getPreço());
		
		
	}
	
	public void editar() {
		
		this.editando = true;
		this.saborAntigo = sabor.get();
	}
	
	public void salvar() {
		PizzaSalgada ps = getentity();
		
		//Condição que permite salvar uma edicao dos valores da pizza
		
		if(this.editando) {
			pizzasalgadaDAO.atualizar(saborAntigo, ps);
			
		} else {
		pizzasalgadaDAO.criar(ps);
		}
		
	}
	

	public void limpar() {
		
		id.set(0);
		sabor.set("");
		borda.set("");
		preço.set(0.0);
		this.editando = false;
		this.saborAntigo = null;
	}
	
	public void pesquisar () {
		//Lista temporaria
		List<PizzaSalgada> tempLista = pizzasalgadaDAO.pesquisarPizzaSalgadaSabor(sabor.get());
		
		lista.clear();
		lista.addAll(tempLista);
		
	}
	

	public void apagar(PizzaSalgada ps) {
		pizzasalgadaDAO.apagar(ps);
	}
	
	
	
	public IntegerProperty idProperty() {
		return id;
		
	}
	
	public StringProperty saborProperty() {
		return sabor;
		
	}
	
	public StringProperty bordaProperty() {
		return borda;
		
	}
	
	public DoubleProperty preçoProperty() {
		return preço;
		
	}


	public ObservableList<PizzaSalgada> getListaPizzaSalgada() {
		return lista;
	}
	
	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}
}
