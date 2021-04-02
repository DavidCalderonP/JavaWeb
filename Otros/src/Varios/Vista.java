package Varios;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Vista extends JDialog {

	JTable table = new JTable();
	DefaultTableModel model = new DefaultTableModel();
	
	public Vista(ArrayList<Object[]> list, Object[] columns) {
		for(Object o : columns) {
			model.addColumn(o);
		}
		
		for (Object[] o : list) {
			model.addRow(o);
		}
		
		table.setModel(model);
		Dimension dime = getToolkit().getScreenSize();
		setSize(new Dimension((int) Math.floor(dime.getWidth()), (int) Math.floor(dime.getWidth())));
		setLayout(new BorderLayout());
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JScrollPane scroll = new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
}
