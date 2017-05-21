package it.polito.tdp.anagrammi.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ParolaDAO {

	public boolean isCorrect(String anagramma) {
		
		String sql = 
				"SELECT nome " + 
				"FROM parola" + 
				" WHERE nome = ?";
		String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root";
		boolean result;
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, anagramma);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
				result = true;
			else
				result = false;
			
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
