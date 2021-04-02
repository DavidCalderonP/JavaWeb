package Sincronizacion;

public class Saludos {

	public static void main(String[] args) {
		Persona p = new Persona();
		Hilo1 t1 = new Hilo1(p);
		Hilo2 t2 = new Hilo2(p);
		t2.start();
		t1.start();
	}

}

class Persona {
	public synchronized void saludar(String nombre) {
		for(int i = 0 ; i < 10 ; i++ ) {
			System.out.println(nombre + " esta saludando.");	
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	} 
}

class Hilo1 extends Thread{

	Persona p;
	
	public Hilo1(Persona p){
		this.p=p;
	}
	public void run() {
		p.saludar("Juan");
	}
}

class Hilo2 extends Thread{
	Persona p;
	
	public Hilo2(Persona p){
		this.p=p;
	}
	public void run() {
		p.saludar("Maria");
	}
}