package Varios;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Prueba{
	
	public static void main(String[] args) throws SQLException {
		Data data = new Data();
		ResultSet aux = data.consulta("Select * from Employees");
		System.out.println("ID\tNombre\tApellido");
		while(aux.next()) {
			System.out.println(aux.getString(1)+"\t"+aux.getString(3)+"\t"+aux.getString(2));
		}
		
		String info = "update Employees Set FirstName = 'C++' where EmployeeID = 1";
		Statement state  = data.obtenerConexion().createStatement();
		state.executeUpdate(info);
		data.obtenerConexion().rollback();
		System.out.println("Despues del update\n");
		ResultSet aux1 = data.consulta("Select * from Employees");
		System.out.println("ID\tNombre\tApellido");
		while(aux1.next()) {
			System.out.println(aux1.getString(1)+"\t"+aux1.getString(3)+"\t"+aux1.getString(2));
		}
		String info2 = "update Employees Set FirstName = 'PYTHON' where EmployeeID = 1";
		Statement state2  = data.obtenerConexion().createStatement();
		state2.executeUpdate(info2);
		data.obtenerConexion().commit();
		System.out.println("Despues del segundo update\n");
		ResultSet aux2 = data.consulta("Select * from Employees");
		System.out.println("ID\tNombre\tApellido");
		while(aux2.next()) {
			System.out.println(aux2.getString(1)+"\t"+aux2.getString(3)+"\t"+aux2.getString(2));
		}
		
		//vista.setVisible(true);
		//vista.setTitle("SQL Server");
	}
}