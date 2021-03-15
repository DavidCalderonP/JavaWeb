package Web;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VistaDetalleLey extends JDialog{

	JTable table;
	DefaultTableModel model = new DefaultTableModel();

	public VistaDetalleLey(ArrayList<Object[]> data, Object[] columns, String report) {

		setBounds(115, 53, 437, 318);
		setLayout(new BorderLayout());
		setModalityType(ModalityType.APPLICATION_MODAL);
		table = new JTable();
		setTitle(report);
		for(Object e : columns) {
			model.addColumn(e);
		}
		
		table.setModel(model);

		for(Object[] element : data) {
			model.addRow(element);
		}
		table.setModel(model);;


		add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);

	}



}