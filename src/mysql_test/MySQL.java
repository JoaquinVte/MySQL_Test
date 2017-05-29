package mysql_test;

import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class MySQL {
	private static Connection Conexion;

	public void MySQLConnection(String user, String pass, String db_name) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
			JOptionPane.showMessageDialog(null, "Se ha iniciado la conexión con el servidor de forma exitosa");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void closeConnection() {
		try {
			Conexion.close();
			JOptionPane.showMessageDialog(null, "Se ha finalizado la conexión con el servidor");
		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void insertData(String table_name, String nombre, String apellidos, String DNI) {
		try {
			String Query = "INSERT INTO " + table_name + " VALUES("

					+ "\"" + nombre + "\", " + "\"" + apellidos + "\", " + DNI + ")";

			System.out.println(Query);

			Statement st = Conexion.createStatement();
			st.executeUpdate(Query);
			JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
		}
	}

	public String getValues(String table_name) {
		String resultado = "";
		try {
			String Query = "SELECT * FROM " + table_name;
			Statement st = Conexion.createStatement();
			java.sql.ResultSet resultSet;
						
			resultSet = st.executeQuery(Query);
			
			while (resultSet.next()) {
				resultado = resultado + "DNI: " + resultSet.getString("DNI") + " " + "Nombre: " + resultSet.getString("nombre")
						+ " " + resultSet.getString("apellidos") + "\n";
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
		}
		return resultado;
	}
	
 public void deleteRecord(String table_name, String DNI) {
        try {
            String Query = "DELETE FROM " + table_name + " WHERE DNI = " + DNI ;
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Registro borrado correctamente!");
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }

}
