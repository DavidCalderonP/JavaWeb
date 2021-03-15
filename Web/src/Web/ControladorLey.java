package Web;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControladorLey implements ActionListener, KeyListener {

	private ModeloLey modeloLey;
	private VistaLey vistaLey;

	public ControladorLey(ModeloLey modeloLey, VistaLey vistaLey) {
		this.modeloLey = modeloLey;
		this.vistaLey = vistaLey;
		vistaLey.activarDesactivarBotones(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int aux;
		Object[] columnNamesR1 = {"No. Producto", "Stock Inicial", "Entradas", "Salidas", "Final"};
		Object[] columnNamesR2 = {"No. Sucursal", "Ventas Totales"};
		Object[] columnNamesR3 = {"No. de Producto(s) que no se vendieron en ninguna sucursal"};
		Object[] columnNamesR4 = {"Del producto", "Al producto"};
		if(e.getSource()==vistaLey.btnExistenciaInicial) {
			this.incializarEstructura();
			modeloLey.asignarCantidadInicial();
			vistaLey.mostrarMensaje("success", "El invetario inicial ha sido generado exitosamente!");
		}
		if(e.getSource()==vistaLey.btnSalidasEntradas) {
			this.incializarEstructura();
			modeloLey.generarEntradasYSalidas();
			vistaLey.mostrarMensaje("success", "Las entradas y las salidas se han generado exitosamente!");
		}
		if(e.getSource()==vistaLey.btnReporte1) {
			this.incializarEstructura();
			if(modeloLey.getInicialCreado()) {
				if(modeloLey.getEstradaSalidaCreado()) {
					vistaLey.crearDetalle(modeloLey.getDataToReport1(), columnNamesR1, "Entradas - Salidas");
				}else {
					aux = vistaLey.mostrarOpciones("Las entradas y salidas no han sido generadas. ¿Desea generarlas?");
					switch (aux) {
						case 0:
							modeloLey.generarEntradasYSalidas();
							vistaLey.mostrarMensaje("success", "Las entradas y las salidas se han generado exitosamente!");
							vistaLey.crearDetalle(modeloLey.getDataToReport1(), columnNamesR1, "Entradas - Salidas");
							break;
						case 1:
							vistaLey.mostrarMensaje("warning", "Se mostraran los reportes con datos incompletos!");
							vistaLey.crearDetalle(modeloLey.getDataToReport1(), columnNamesR1, "Entradas - Salidas");
							break;
						case 2:
							break;
							
					}
				}
			}else {
				aux = vistaLey.mostrarOpciones("El inventario inicial no ha sido generado. ¿Desea generarlo?");
				switch(aux) {
				case 0:
					modeloLey.asignarCantidadInicial();
					vistaLey.mostrarMensaje("success", "El inventario inicial se genero exitosamente!");
					if(modeloLey.getEstradaSalidaCreado()) {
						vistaLey.crearDetalle(modeloLey.getDataToReport1(), columnNamesR1, "Entradas - Salidas");
					}else {
						switch(vistaLey.mostrarOpciones("Las entradas y salidas no han sido generadas. ¿Desea generarlas?")){
						case 0:
							modeloLey.generarEntradasYSalidas();
							vistaLey.mostrarMensaje("success", "Las entradas y las salidas se han generado exitosamente!");
							vistaLey.crearDetalle(modeloLey.getDataToReport1(), columnNamesR1, "Entradas - Salidas");
							break;
						case 1:
							vistaLey.mostrarMensaje("warning", "Se mostraran los reportes con datos incompletos!");
							vistaLey.crearDetalle(modeloLey.getDataToReport1(), columnNamesR1, "Entradas - Salidas");
							break;
						case 2:
							break;
						}
						
					}
					break;
				case 1:
					vistaLey.mostrarMensaje("warning", "Se mostraran los reportes con datos incompletos!");
					vistaLey.crearDetalle(modeloLey.getDataToReport1(), columnNamesR1, "Entradas - Salidas");
					break;
				}
			}
		}
		if(e.getSource()==vistaLey.btnReporte2) {
			if(!(modeloLey.dataIsNull())) {
				vistaLey.crearDetalle(modeloLey.getDataToReport2(), columnNamesR2, "Sucursal(es) con mas ventas");
			}else {
				vistaLey.mostrarMensaje("warning", "Aegurese de haber generado el inventario incial, salidas y entradas.");
			}
		}
		
		if(e.getSource()==vistaLey.btnReporte3) {
			if(!(modeloLey.dataIsNull())) {
				vistaLey.crearDetalle(modeloLey.getDataToReport3(), columnNamesR3, "Productos sin ventas");
			}else {
				vistaLey.mostrarMensaje("warning", "Aegurese de haber generado el inventario incial, salidas y entradas.");
			}
				
		}
		
		if(e.getSource()==vistaLey.btnReporte4) {
			if(!(modeloLey.dataIsNull())) {
				vistaLey.crearDetalle(modeloLey.Reporte4Rangos(), columnNamesR4, "Rangos de productos sin ventas");
			}else {
				vistaLey.mostrarMensaje("warning", "Aegurese de haber generado el inventario incial, salidas y entradas.");
			}
		}
	}
	
	public void incializarEstructura() {
		if(modeloLey.dataIsNull()) {
			modeloLey.crearEstructura(Integer.parseInt(vistaLey.txtNoSucursales.getText()), Integer.parseInt(vistaLey.txtNoProductos.getText()));
		}
	}
	public boolean valorDeProductosAceptable(String txtFieldText) {
		boolean res = true;
		try {
			if(Integer.parseInt(txtFieldText)<1)
				res = false;
		} catch (Exception e) {
			res = false;
		}
		return res;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource().equals(vistaLey.txtNoProductos)){
			if(!valorDeProductosAceptable(vistaLey.txtNoProductos.getText()) && vistaLey.txtNoProductos.getText().length()>0) {
				vistaLey.hayHerrores(vistaLey.txtNoProductos);
			}else {
				vistaLey.estaCorrecto(vistaLey.txtNoProductos);
			}
		}
		if(e.getSource().equals(vistaLey.txtNoSucursales)) {
			if(!valorDeProductosAceptable(vistaLey.txtNoSucursales.getText()) && vistaLey.txtNoSucursales.getText().length()>0) {
				vistaLey.hayHerrores(vistaLey.txtNoSucursales);

			}else {
				vistaLey.estaCorrecto(vistaLey.txtNoSucursales);
			}
		}
		if(!valorDeProductosAceptable(vistaLey.txtNoProductos.getText()) || !valorDeProductosAceptable(vistaLey.txtNoSucursales.getText())) {
			vistaLey.activarDesactivarBotones(false);
		}else {
			vistaLey.activarDesactivarBotones(true);
		}
		modeloLey.setDataNull();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}