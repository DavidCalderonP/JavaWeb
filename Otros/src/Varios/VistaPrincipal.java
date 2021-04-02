package Varios;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private Data data = new Data();

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal frame = new VistaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */

	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		try {
			for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {

				//Metal
				//Nimbus
				//CDE/Motif
				//Windows
				//Windows Classic

				if("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}catch(Exception e) {

		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDatos = new JMenu("Datos");
		menuBar.add(mnDatos);

		JMenuItem mntmEmpleados = new JMenuItem("Empleados");
		mntmEmpleados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ResultSet datos = data.consulta("Select * from Employees");
				ArrayList<Object[]> list = new ArrayList<Object[]>();
				try {
					while(datos.next()) {
						list.add(new Object[] {datos.getString(1)
								,datos.getString(2),
								datos.getString(3),
								datos.getString(4),
								datos.getString(5),
								datos.getString(6),
								datos.getString(7),
								datos.getString(8),
								datos.getString(9),
								datos.getString(10),
								datos.getString(11),
								datos.getString(12),
								datos.getString(13),
								datos.getString(14),
								datos.getString(15),
								datos.getString(16),
								datos.getString(17),
								datos.getString(18)
						});
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				new VistaDetalle(list, new Object[] {"EmployeeID", 
						"LastName", 
						"FirstName", 
						"Title", 
						"TitleOfCourtesy", 
						"BirthDate", 
						"HireDate", 
						"Address", 
						"City", 
						"Region", 
						"PostalCode", 
						"Country", 
						"HomePhone", 
						"Extension", 
						"Photo", 
						"Notes", 
						"ReportsTo", 
						"PhotoPath"});
				 
			}
		});

		mnDatos.add(mntmEmpleados);

		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ResultSet datos = data.consulta("Select * from Customers");
				ArrayList<Object[]> list = new ArrayList<Object[]>();
				try {
					while(datos.next()) {
						list.add(new Object[] {datos.getString(1)
								,datos.getString(2),
								datos.getString(3),
								datos.getString(4),
								datos.getString(5),
								datos.getString(6),
								datos.getString(7),
								datos.getString(8),
								datos.getString(9),
								datos.getString(10),
								datos.getString(11)
						});
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new VistaDetalle(list, new Object[] {"CustomerID", 
						"CompanyName", 
						"ContactName", 
						"ContactTitle", 
						"Address", 
						"City", 
						"Region", 
						"PostalCode", 
						"Country", 
						"Phone", 
						"Fax"});
				
			}
		});
		mnDatos.add(mntmClientes);

		JMenu mnAcercaDe = new JMenu("Acerca de");
		menuBar.add(mnAcercaDe);

		JMenu mnOpcion = new JMenu("Opcion");
		mnAcercaDe.add(mnOpcion);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}
}
