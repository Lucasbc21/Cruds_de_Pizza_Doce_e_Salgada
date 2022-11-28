package edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class PizzaSalgadaDAOImplement implements PizzaSalgadaDAO {
	
	//Metodo de conexao com bando de dados
	
	public final static String hostName = "localhost";
	public final static String dbName = "PIZZARIA";
	public final static String user = "Lucas";
	public final static String senha = "123456";
	private Connection con; //variavel que permite a conexao com banco de dados

	
	public PizzaSalgadaDAOImplement() {

		try {
		Class.forName("net.sourceforge.jtds.jdbc.Driver");	
		con = DriverManager.getConnection(
				String.format("jdbc:jtds:sqlserver://%s:1433;"
						+ "databaseName=%s;"
						+ "user=%s;"
						+ "password=%s;",
						 hostName, dbName, user, senha));
		} catch(ClassNotFoundException | SQLException e) {
			
		}
	}

	
	//Metodo DAO de inserção conectado ao botao Salvar
	@Override
	public void criar(PizzaSalgada ps) {
		
		
		//INSERT INTO que preenche as informacoes da PIZZASALGADA e o ID da PIZZA
		
		String sql = "INSERT INTO PIZZA(ID)"
				+ "VALUES("+ ps.getId() +")"
				+ ""
				+ "INSERT INTO PIZZASALGADA "
				+ "   (ID_PIZZA_SAL, SABOR, BORDA, PREÇO) "
				+ "VALUES ("+ ps.getId() +", '"+ ps.getSabor() +"', '"+ ps.getBorda() +"', "+ ps.getPreço() +")";
			
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "NÃO É PERMITIDO ID REPETIDO", 
					"ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	

	//Metodo DAO que realiza o pesquisar
	@Override
	public List<PizzaSalgada> pesquisarPizzaSalgadaSabor(String sabor) {
		
		List<PizzaSalgada> lista = new ArrayList<>();
		
		//SELECT * FROM QUE que pesquisar com no sabor sem nenhum valor tras lista todos os registros
		
		String sql = "SELECT * "
				   + "FROM PIZZASALGADA "
				   + "WHERE SABOR LIKE '%"+ sabor +"%'";
		
		try {
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    ResultSet rs = pstmt.executeQuery();
		   while(rs.next()) {
			   PizzaSalgada ps = new PizzaSalgada();
			   
			   ps.setSabor(rs.getString("SABOR"));
			   ps.setId(rs.getInt("ID_PIZZA_SAL"));
			   ps.setBorda(rs.getString("BORDA"));
			   ps.setPreço(rs.getDouble("PREÇO"));
			   lista.add(ps);
			   
		   }
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO NO RETORNO", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
		return lista;
	}
	
	
	//Metodo DAO que apagar os registros incluindo a chave primaria
	public void apagar(PizzaSalgada ps) {
		
		//DELETE que apgas as pizzas dos tanto da tabela PIZZA quanto da tabela PIZZASALGADA
		
		String sql = "DELETE FROM PIZZASALGADA "
				   + "WHERE ID_PIZZA_SAL = "+ ps.getId() +"";
		
		String sql2 = "DELETE FROM PIZZA "
				   + "WHERE ID = "+ ps.getId() +"";
		
		
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.executeUpdate();	
			
		PreparedStatement pstmt2 = con.prepareStatement(sql2);
		pstmt2.executeUpdate();
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO NA DELETAÇÃO", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	//Metodo DAO que permite na edicao dos valores haver atualizar deles menos do id pois é chave primaria
	public void atualizar(String saborAntigo, PizzaSalgada ps) {
		
		
		//UPDATE que atualizar as informacoes da pizza salgada menos seu ID por ser a chave primaria
		
		String sql = "UPDATE PIZZASALGADA "
				   + "SET ID_PIZZA_SAL = "+ps.getId()+", "
				       + "SABOR = '"+ps.getSabor()+"', "
				       + "BORDA = '"+ps.getBorda()+"', "
				       + "PREÇO =  "+ps.getPreço()+""
				   + "WHERE SABOR = '"+saborAntigo+"'";
		

		
	
		
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "NÃO ALTERAR A CHAVE PRIMARIA", 
					"ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

}
