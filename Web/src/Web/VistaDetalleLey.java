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

	public VistaDetalleLey(ArrayList<Object[]> data, int report) {

		setBounds(115, 53, 437, 318);
		setLayout(new BorderLayout());
		setModalityType(ModalityType.APPLICATION_MODAL);
		table = new JTable();
		Object[] columnNames;
		
		switch(report) {
		case 1:
			setTitle("Entradas - Salidas");
			Object[] columnNamesR1 = {"No. Producto", "Stock Inicial", "Entradas", "Salidas", "Final"};
			for(Object e : columnNamesR1 ) {
				model.addColumn(e);
			}
			break;
		case 2:
			Object[] columnNamesR2 = {"No. Sucursal", "Ventas Totales"};
			setTitle("Sucursal(es) con mas ventas");
			for(Object e : columnNamesR2 ) {
				model.addColumn(e);
			}
			break;
		case 3:
			Object[] columnNamesR3 = {"No. de Producto(s) que no se vendieron en ninguna sucursal"};
			setTitle("Productos sin ventas");
			for(Object e : columnNamesR3 ) {
				model.addColumn(e);
			}
			break;
		case 4:
			Object[] columnNamesR4 = {"Del producto", "Al producto"};
			setTitle("Rangos de productos sin ventas");
			for(Object e : columnNamesR4) {
				model.addColumn(e);
			}
			break;
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