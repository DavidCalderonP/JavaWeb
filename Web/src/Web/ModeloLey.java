package Web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class ModeloLey {

	private Producto[][] data;
	private boolean inicialCreado;
	private boolean entradaSalidaCreado;

	public ModeloLey() {
		data =  null;
		inicialCreado = false;
		entradaSalidaCreado = false;
	}

	public void crearEstructura(int s, int p) {
		data = new Producto[s][p];
		for(int i = 0 ; i < data.length ; i++) {
			for(int j = 0 ; j < data[0].length ; j++) {
				insertarProducto(i, j, new Producto());
			}
		}
	}

	public void setDataNull() {
		data = null;
		inicialCreado = false;
		entradaSalidaCreado = false;
	}

	public void insertarProducto(int r, int c, Producto producto) {
		data[r][c] = producto;
	}

	public boolean getEstradaSalidaCreado() {
		return entradaSalidaCreado;
	}

	public void setEntradaSalidaCreado(boolean flag) {
		this.entradaSalidaCreado = flag;
	}

	public boolean getInicialCreado() {
		return inicialCreado;
	}

	public void setInicialCreado(boolean flag) {
		this.inicialCreado = flag;
	}

	public void asignarCantidadInicial() {
		for(int i = 0 ; i < data.length ; i++) {
			for(int j = 0 ; j < data[0].length ; j++) {
				data[i][j].setCantidadInicial();
			}
		}
		this.setInicialCreado(true);
	}

	public void reporte1() {
		for(int i=0;i<data.length; i++) {
			System.out.println("Sucursal: " + (i+1));
			for(int j = 0; j<data[0].length;j++) {
				System.out.println(data[i][j].toString());
			}
			System.out.println();
		}
	}

	public void generarEntradasYSalidas() {
		int entradasYSalidas = (int)Math.round(Math.random()*100)+300; //Cantidad de entradas y salidas
		int entradaOSalida = 0; //Define si es entrada o salida
		int cantidad = 0; //Cuanto se agregará
		int sucursal = 0; //Numero de sucursal a asignar
		int producto = 0; //Numero de producto a asignar

		for(int i = 0 ; i < entradasYSalidas ; i++) {
			entradaOSalida = (int) Math.round(Math.random()*1)+1;
			cantidad = (int) Math.round(Math.random()*2)+1;
			sucursal = (int) Math.round(Math.random()*(data.length-1));
			producto =(int) Math.round(Math.random()*(data[0].length-1));
			if(entradaOSalida == 1) {
				data[sucursal][producto].setCantEntrada(cantidad);
			}else {
				data[sucursal][producto].setCantidadSalida(cantidad);
			}

		}
		setEntradaSalidaCreado(true);
	}

	public Producto[][] getData(){
		return data;
	}
	public ArrayList<Object[]> getDataToReport1(){
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		int entrada = 0, salida = 0;
		for(int i = 0; i < data.length ; i++) {
			list.add(new Object[] {"Sucursal No. " + (i+1)});
			entrada = salida = 0;
			for(int j = 0; j < data[i].length ; j++) {
				
				entrada += data[i][j].getCantEntrada();
				salida += data[i][j].getCantidadSalida();
				list.add(new Object[] {(j+1),data[i][j].getCantInicial(), data[i][j].getCantEntrada(), data[i][j].getCantidadSalida(), data[i][j].getCantidadFinal()});
			}
			list.add(new Object[] {});
			list.add(new Object[] {"Total:","",entrada, salida});
			list.add(new Object[] {});
		}

		return list;
	}

	public ArrayList<Object[]> getDataToReport2() {
		ArrayList<Object[]> list  = new ArrayList<Object[]>();
		int ventas, ventasMaximas = 0, aux;
		for(int i = 0 ; i < data.length ; i++) {
			ventas = 0;
			for(int j = 0 ; j < data[0].length ; j++) {
				ventas += data[i][j].getCantidadSalida();
			}
			if(ventasMaximas < ventas) {
				ventasMaximas = ventas;
			}

		}
		for(int i = 0 ; i < data.length ; i++){
			aux = 0;
			for(int j = 0 ; j < data[0].length ; j++){
				aux += data[i][j].getCantidadSalida();
			}
			if(aux==ventasMaximas) {
				list.add(new Object[] {(i+1), aux});
			}
		}
		return list;
	}

	public ArrayList<Object[]> getDataToReport3(){
		ArrayList<Object[]> list  = new ArrayList<Object[]>();
		boolean articulo;
			articulo = true;
			for(int j = 0 ; j < data[0].length ; j++) {
				if(data[0][j].getCantidadSalida()==0) {
					for(int k = 1 ; k < data.length ; k++) {
						if(!(data[k][j].getCantidadSalida()==0)) {
							articulo = false;
						}
					}
					if(articulo)
						list.add(new Object[] {(j+1)});
					articulo = true;
				}
			}
		if(list.size()==0)
			list.add(new Object[] {"No se encontraron registros"});
		return list;
	}
	
	public ArrayList<Object[]> Reporte4Rangos(){
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		int inicio = 0, fin = 0;
		for(int i=0; i< data.length; i++){
			list.add(new Object[] {"Sucursal: " + (i+1)});
			for(int j=0; j < data[0].length; j++){
				if(data[i][j].getCantidadSalida() == 0){
					inicio = j;
					while(j < data[0].length && data[i][j].getCantidadSalida() == 0){
						fin=j;
						j++;
					}
					fin++;
					inicio++;
					list.add(new Object[] {inicio, fin});
				}
			}
			list.add(new Object[] {});
		}
		return list;
	}
}