package edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteConexao {
	
	public final static String hostName = "localhost";
	public final static String dbName = "PIZZARIA";
	public final static String user = "Lucas";
	public final static String senha = "123456";
	
	
	private Connection c;
	
		
		public Connection getConnection() throws ClassNotFoundException, SQLException {
			
			
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			c = DriverManager.getConnection(
					String.format("jdbc:jtds:sqlserver://%s:1433;"
							+ "databaseName=%s;"
							+ "user=%s;"
							+ "password=%s;",
							 hostName, dbName, user, senha));
					
					
					return c;
			
		
	}
		public static void main(String[] args) {
			
			TesteConexao tc = new TesteConexao();
			
			PizzaDoce pd = new PizzaDoce();
			try {
				System.out.println("Conexao feita");
				Connection con = tc.getConnection();
				System.out.println("Conectado com banco de dados " + con + " bem sucedida:)");
				
				pd.setId(55);
				pd.setCobertura("chave");
				pd.setBorda("pato");
				pd.setPreço(1.2);
				
				String sql = "INSERT INTO PIZZA(ID)"
						+ "VALUES("+ pd.getId() +")"
						+ ""
						+ "INSERT INTO PIZZADOCE "
						+ "   (ID_PIZZA_DOCE, COBERTURA, BORDA, PREÇO) "
						+ "VALUES ("+ pd.getId() +", '"+ pd.getCobertura() +"', '"+ pd.getBorda() +"', "+ pd.getPreço() +")";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
						
						
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			}
		}


