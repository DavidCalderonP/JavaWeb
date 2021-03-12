package Web;

public class Producto {

	private int cantInicial;
	private int cantEntrada;
	private int cantidadSalida;
	
	public Producto() {
		cantInicial = 0;
		cantEntrada = 0;
		cantidadSalida = 0;

	}
	
	public int getCantInicial() {
		return cantInicial;
	}
	
	public void setCantidadInicial() {
		int res = (int)Math.round(Math.random()*100)+100;
		cantInicial = res;
	}

	public int getCantEntrada() {
		return cantEntrada;
	}
	
	public void setCantEntrada(int cantEntrada) {
		this.cantEntrada += cantEntrada;
	}

	public int getCantidadSalida() {
		return cantidadSalida;
	}
	
	public void setCantidadSalida(int cantidadSalida) {
		this.cantidadSalida += cantidadSalida;
	}

	public int getCantidadFinal() {
		return cantInicial + cantEntrada - cantidadSalida;
	}

	public String toString() {
		return cantInicial + "\t" + cantEntrada + "\t" + cantidadSalida + "\t" + getCantidadFinal();
	}







}