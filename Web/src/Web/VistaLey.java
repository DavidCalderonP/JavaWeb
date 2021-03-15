package Web;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VistaLey extends JFrame{

	JButton btnExistenciaInicial, btnSalidasEntradas, btnReporte1, btnReporte2, btnReporte3, btnReporte4;
	JTextField txtNoSucursales, txtNoProductos;
	JLabel resultado;

	public VistaLey() {

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

		setSize(437, 318);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(0,1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		txtNoSucursales = new JTextField();
		txtNoProductos = new JTextField();

		btnExistenciaInicial = new JButton("Generar Existencia Inicial");
		btnSalidasEntradas = new JButton("Generar Salidas - Entradas");
		btnReporte1 = new JButton("Imprimir por Sucursal Comportamiento de Productos");
		btnReporte2 = new JButton("Imprimir Sucursal(es) con mas unidades vendidas");
		btnReporte3 = new JButton("Imprimir Productos que no se vendieron en ninguna sucursal");
		btnReporte4 = new JButton("Imprimir Sucursal-Rangos de productos no vendidos");

		add(new JLabel("No. Sucursales: "));
		add(txtNoSucursales);
		add(new JLabel("No. Productos: "));
		add(txtNoProductos);
		add(btnExistenciaInicial);
		add(btnSalidasEntradas);
		add(btnReporte1);
		add(btnReporte2);
		add(btnReporte3);
		add(btnReporte4);

		setVisible(true);
		
		txtNoProductos.setToolTipText("Cantidad de productos");
		txtNoSucursales.setToolTipText("Cantidad de sucursales");

	}

	public void hayHerrores(JTextField txtField) {
		txtField.setToolTipText("Valor no admitido, por favor ingrese un numero natural.");
		txtField.setBackground(Color.RED);
	}

	public void estaCorrecto(JTextField txtField) {
		if(txtField.equals(txtNoProductos)){
			txtField.setToolTipText("Cantidad de productos");
		}else {
			txtField.setToolTipText("Cantidad de sucursales");
		} 
		txtField.setBackground(Color.WHITE);
	}

	public void setVisible() {
		this.setVisible(true);
	}

	public void setControladorLey(ControladorLey c) {
		txtNoSucursales.addKeyListener(c);
		txtNoProductos.addKeyListener(c);
		btnExistenciaInicial.addActionListener(c);
		btnSalidasEntradas.addActionListener(c);
		btnReporte1.addActionListener(c);
		btnReporte2.addActionListener(c);
		btnReporte3.addActionListener(c);
		btnReporte4.addActionListener(c);
	}

	public String getText(JTextField txtField) {
		return txtField.getText();
	}

	public int mostrarOpciones(String message) {
		return JOptionPane.showConfirmDialog(null, message, "Advertencia", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}
	
	public int mostrarMensaje(String type, String message) {
		if(type.equals("warning")) {
			return JOptionPane.showConfirmDialog(null, message, "Advertencia", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}else {
			return JOptionPane.showConfirmDialog(null, message, "Hecho", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public void crearDetalle(ArrayList<Object[]> data, Object[] columns, String report) {
		new VistaDetalleLey(data, columns, report);
	}
	
	public void activarDesactivarBotones(boolean flag) {
		btnReporte1.setEnabled(flag);
		btnReporte2.setEnabled(flag);
		btnReporte3.setEnabled(flag);
		btnReporte4.setEnabled(flag);
		btnExistenciaInicial.setEnabled(flag);
		btnSalidasEntradas.setEnabled(flag);
	}
}