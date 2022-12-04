package edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteConexao {
	
	public final static String URL = 
			"jdbc:mariadb://localhost:3306/Pizzaria";
	public final static String USER = "root";
	public final static String PASS = "123456";
	
	public static void main(String[] args) 
			throws ClassNotFoundException {
		Class.forName("org.mariadb.jdbc.Driver");
		System.out.println("Classe carregada");
		
		PizzaDoce pd = new PizzaDoce();
		
		try (Connection con = 
				DriverManager.getConnection(URL, USER, PASS)) {
			System.out.println("Conectado no banco de dados");

			
			pd.setId(60);
			pd.setCobertura("cachorro");
			pd.setBorda("nananan");
			pd.setPreço(77.8);
			
			System.out.println(con);
			
			String sql = "INSERT INTO PIZZA(ID)"
					+ "VALUES("+ pd.getId() +")"
					+ "";
			
			String sql2  =
					 "INSERT INTO PIZZADOCE "
					+ "   (ID_PIZZA_DOCE, COBERTURA, BORDA, PREÇO) "
					+ "VALUES ("+ pd.getId() +", '"+ pd.getCobertura() +"', '"+ pd.getBorda() +"', "+ pd.getPreço() +")";
			
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			pstmt2.executeUpdate();
			
			
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
}
