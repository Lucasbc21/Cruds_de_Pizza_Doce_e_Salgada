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

public class PizzaDoceControl {
	

	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty cobertura = new SimpleStringProperty("");
	private StringProperty borda = new SimpleStringProperty("");
	private DoubleProperty preço = new SimpleDoubleProperty(0.0);
	
	private PizzaDoceDAO pizzaDoceDAO = new PizzaDoceDAOImplement();
	
	
	private boolean edicao = false;
	private String CoberturaAntiga = null;
	
	

	private ObservableList<PizzaDoce> lista = 
			FXCollections.observableArrayList();
	
	
	public PizzaDoce getEntity() {
		
		PizzaDoce pd = new PizzaDoce();
		
		pd.setId(id.get());
		pd.setCobertura(cobertura.get());
		pd.setBorda(borda.get());
		pd.setPreço(preço.get());
		
		
		return pd;
	}
	
	
	public void setEntity(PizzaDoce pd) {
		
		id.set(pd.getId());
		cobertura.set(pd.getCobertura());
		borda.set(pd.getBorda());
		preço.set(pd.getPreço());	
		
	}
	
	public void editar() {
		this.edicao = true;
		this.CoberturaAntiga = cobertura.get();
		
	}
	
	public void salvar() {	

		PizzaDoce pd = getEntity();
		
		//Condição que permite salvar uma edicao dos valores da pizza
		
		if(this.edicao) {
			
			pizzaDoceDAO.atualizar(CoberturaAntiga, pd);
			
		}else {
		
		pizzaDoceDAO.criar(pd);
		
		}
	}
	
	//Metodo que limpara os campos
	public void limpar() {
		
		id.set(0);
		cobertura.set("");
		borda.set("");
		preço.set(0.0);
	}
	
	public void pesquisar () {
		//Lista temporaria
		List<PizzaDoce> tempLista = pizzaDoceDAO.pesquisarPizzaporCobertura(cobertura.get());
		
		lista.clear();
		lista.addAll(tempLista);
	}
	
	public void apagar(PizzaDoce pd) {
		
		pizzaDoceDAO.apagar(pd);
	}
	
	
	public IntegerProperty idProperty() {
		return id;
		
	}
	
	public StringProperty coberturaProperty() {
		return cobertura;
		
	}
	
	public StringProperty bordaProperty() {
		return borda;
		
	}
	
	public DoubleProperty preçoProperty() {
		return preço;
	}
	
	public ObservableList<PizzaDoce> getListaPizzaDoce() {
		return lista;
	}
	
}
