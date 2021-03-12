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
		if(e.getSource()==vistaLey.btnExistenciaInicial) {
			this.incializarEstructura();
			modeloLey.asignarCantidadInicial();
			vistaLey.showMessageOption("success", "El invetario inicial ha sido generado exitosamente!");
		}
		if(e.getSource()==vistaLey.btnSalidasEntradas) {
			this.incializarEstructura();
			modeloLey.generarEntradasYSalidas();
			vistaLey.showMessageOption("success", "Las entradas y las salidas se han generado exitosamente!");
		}
		if(e.getSource()==vistaLey.btnReporte1) {
			this.incializarEstructura();
			if(modeloLey.getInicialCreado()) {
				if(modeloLey.getEstradaSalidaCreado()) {
					vistaLey.crearDetalle(modeloLey.getDataToReport1(), 1);
				}else {
					aux = vistaLey.showOption("Las entradas y salidas no han sido generadas. ¿Desea generarlas?");
					switch (aux) {
						case 0:
							modeloLey.generarEntradasYSalidas();
							vistaLey.showMessageOption("success", "Las entradas y las salidas se han generado exitosamente!");
							vistaLey.crearDetalle(modeloLey.getDataToReport1(), 1);
							break;
						case 1:
							vistaLey.showMessageOption("warning", "Se mostraran los reportes con datos incompletos!");
							vistaLey.crearDetalle(modeloLey.getDataToReport1(), 1);
							break;
						case 2:
							break;
							
					}
				}
			}else {
				aux = vistaLey.showOption("El inventario inicial no ha sido generado. ¿Desea generarlo?");
				switch(aux) {
				case 0:
					modeloLey.asignarCantidadInicial();
					vistaLey.showMessageOption("success", "El inventario inicial se genero exitosamente!");
					if(modeloLey.getEstradaSalidaCreado()) {
						vistaLey.crearDetalle(modeloLey.getDataToReport1(), 1);
					}else {
						switch(vistaLey.showOption("Las entradas y salidas no han sido generadas. ¿Desea generarlas?")){
						case 0:
							modeloLey.generarEntradasYSalidas();
							vistaLey.showMessageOption("success", "Las entradas y las salidas se han generado exitosamente!");
							vistaLey.crearDetalle(modeloLey.getDataToReport1(), 1);
							break;
						case 1:
							vistaLey.showMessageOption("warning", "Se mostraran los reportes con datos incompletos!");
							vistaLey.crearDetalle(modeloLey.getDataToReport1(), 1);
							break;
						case 2:
							break;
						}
						
					}
					break;
				case 1:
					vistaLey.showMessageOption("warning", "Se mostraran los reportes con datos incompletos!");
					vistaLey.crearDetalle(modeloLey.getDataToReport1(), 1);
					break;
				}
			}
		}
		if(e.getSource()==vistaLey.btnReporte2) {
			if(!(modeloLey.getData()==null)) {
				vistaLey.crearDetalle(modeloLey.getDataToReport2(), 2);
			}
		}
		
		if(e.getSource()==vistaLey.btnReporte3) {
			if(!(modeloLey.getData()==null))
				vistaLey.crearDetalle(modeloLey.getDataToReport3(), 3);
		}
		
		if(e.getSource()==vistaLey.btnReporte4) {
			if(!(modeloLey.getData()==null)) {
				vistaLey.crearDetalle(modeloLey.Reporte4Rangos(), 4);
			}
		}
	}
	
	public void incializarEstructura() {
		if(modeloLey.getData()==null) {
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