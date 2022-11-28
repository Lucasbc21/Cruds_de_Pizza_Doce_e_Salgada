package edu.curso;

public class Pizza {
	
	private int id = 0;
	private String sabor = "";
	private String borda = "";
	private double preço = 0.0;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getSabor() {
		return sabor;
	}
	
	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
	
	public String getBorda() {
		return borda;
	}
	
	public void setBorda(String borda) {
		this.borda = borda;
	}
	
	public Double getPreço() {
		return preço;
	}
	
	public void setPreço(double preço) {
		this.preço = preço;
	}

}
