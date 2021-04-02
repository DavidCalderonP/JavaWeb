package Varios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaDetalle extends JDialog {
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private Data data = new Data();

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			VistaDetalle dialog = new VistaDetalle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

	/**
	 * Create the dialog.
	 */
	public VistaDetalle(ArrayList<Object[]> list, Object[] columns) {
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		
		for(Object obj : columns) {
			model.addColumn(obj);
		}
		for(Object[] obj : list) {
			model.addRow(obj);
		}
		
		JButton btnNewButton = new JButton("Cancelar");
		getContentPane().add(btnNewButton, BorderLayout.WEST);
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JButton btnC = new JButton("Confirmar");
		getContentPane().add(btnC, BorderLayout.SOUTH);
		
		JButton btnEmpleado = new JButton("Empleado 1");
		btnEmpleado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String info = "begin tran"
						+ "UPDATE Employees SET FirstName = 'JAVA' where EmployeeID = 1";
				
				
			}
		});
		getContentPane().add(btnEmpleado, BorderLayout.NORTH);
		//pack();
		//getContentPane().add(table, BorderLayout.CENTER);
		setVisible(true);
	}

}
