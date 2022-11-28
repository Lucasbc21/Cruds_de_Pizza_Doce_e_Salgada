package edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class PizzaDoceDAOImplement implements PizzaDoceDAO {
	
	//Metodo de conexao com banco de dados 
	
	public final static String hostName = "localhost";
	public final static String dbName = "PIZZARIA";
	public final static String user = "Lucas";
	public final static String senha = "123456";
	private Connection con;   //variavel que permite a conexao
	
	public PizzaDoceDAOImplement () {

	try {
	Class.forName("net.sourceforge.jtds.jdbc.Driver");	
	con = DriverManager.getConnection(
			String.format("jdbc:jtds:sqlserver://%s:1433;"
					+ "databaseName=%s;"
					+ "user=%s;"
					+ "password=%s;",
					 hostName, dbName, user, senha));
	} catch(ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
}

	//Metodo DAO de inserção conectado ao botao Salvar
	@Override
	public void criar(PizzaDoce pd) {
		
		//INSERT INTO que preenche as informacoes da PIZZADOCE e o ID da PIZZA
		
		String sql = "INSERT INTO PIZZA(ID)"
				+ "VALUES("+ pd.getId() +")"
				+ ""
				+ "INSERT INTO PIZZADOCE "
				+ "   (ID_PIZZA_DOCE, COBERTURA, BORDA, PREÇO) "
				+ "VALUES ("+ pd.getId() +", '"+ pd.getCobertura() +"', '"+ pd.getBorda() +"', "+ pd.getPreço() +")";
		
		try {
			
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.executeUpdate();
		
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "NÃO É PERMITIDO ID REPETIDO", 
					"ERRO", JOptionPane.ERROR_MESSAGE);	
		}
		
	}

	
	//Metodo DAO que realiza o pesquisar
	@Override
	public List<PizzaDoce> pesquisarPizzaporCobertura(String cobertura) {
		
		List<PizzaDoce> lista = new ArrayList<>();
		
		
		//SELECT * FROM QUE que pesquisar com no sabor sem nenhum valor tras lista todos os registros
		String sql = "SELECT * "
				   + "FROM PIZZADOCE "
				   + "WHERE COBERTURA LIKE '%"+ cobertura +"%'";
		
		try {
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    ResultSet rs = pstmt.executeQuery();
		   while(rs.next()) {
			   PizzaDoce pd = new PizzaDoce();
			   
			   pd.setId(rs.getInt("ID_PIZZA_DOCE"));
			   pd.setCobertura(rs.getString("COBERTURA")); 
			   pd.setBorda(rs.getString("BORDA"));
			   pd.setPreço(rs.getDouble("PREÇO"));
			   lista.add(pd);
			   
		   }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	
	//Metodo DAO que apagar os registros incluindo a chave primaria
	@Override
	public void apagar(PizzaDoce pd) {
		
		//DELETE que apgas as pizzas dos tanto da tabela PIZZA quanto da tabela PIZZADOCE
		
		
		String sql = "DELETE FROM PIZZADOCE "
				   + "WHERE ID_PIZZA_DOCE = "+ pd.getId() +"";
		
		String sql2 = "DELETE FROM PIZZA "
				   + "WHERE ID = "+ pd.getId() +"";
		
		try {
			
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.executeUpdate();
		
		PreparedStatement pstmt2 = con.prepareStatement(sql2);
		pstmt2.executeUpdate();
		
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO NA DELETAÇÃO", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	
	//Metodo DAO que permite na edicao dos valores haver atualizar deles menos do id pois é chave primaria
	@Override
	public void atualizar(String coberturaAntiga, PizzaDoce pd) {
		
		
		//UPDATE que atualizar as informacoes da pizza doce menos seu ID por ser a chave primaria
		String sql = "UPDATE PIZZADOCE "
				   + "SET ID_PIZZA_DOCE = "+pd.getId()+", "
				       + "COBERTURA = '"+pd.getCobertura()+"', "
				       + "BORDA = '"+pd.getBorda()+"', "
				       + "PREÇO =  "+pd.getPreço()+""
				   + "WHERE COBERTURA = '"+coberturaAntiga+"'";
		
		
		
		try {
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "NÃO ALTERAR A CHAVE PRIMARIA", 
					"ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
