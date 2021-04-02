package Varios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Data {

	private Connection con;
	private ResultSet ultimaConsulta;
	
	public void limpiarConsulta() {
		ultimaConsulta = null;
	}
	
	public ResultSet consulta(String consulta) {
		Connection conexion = crearConexion();
		Statement statement;
		ultimaConsulta = null;
		try {
			conexion.setAutoCommit(false);
			statement = conexion.createStatement();
			ultimaConsulta = statement.executeQuery(consulta);
		} catch (Exception e) {
			ultimaConsulta = null;
		}
		return ultimaConsulta;
	}
	
	public Connection crearConexion() {
		String conexionurl = "jdbc:sqlserver://25.16.242.166;databaseName=Northwind;user=sa;password=1234";
		try {
			System.out.println("Conectando...");
			con = DriverManager.getConnection(conexionurl);
			con.setAutoCommit(false);
		} catch (Exception e) {
			con = null;
			System.out.println("Error en la conexion!");
		}
		System.out.println("Conexion Exitosa!");
		return con;
	}	
	
	public Connection obtenerConexion() {
		return con;
	}
	
	public void olvidarConexion() {
		con = null;
	}
	
	public void cerrarConexion() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
