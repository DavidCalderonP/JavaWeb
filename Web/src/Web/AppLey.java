package Web;

import java.util.ArrayList;

public class AppLey {

	public static void main(String[] args) {
									// P, S
		ModeloLey mley = new ModeloLey();
		VistaLey vley = new VistaLey();
		ControladorLey cley = new ControladorLey(mley, vley);
		
		vley.setTitle("Casa Ley - Inventarios");
		vley.setControladorLey(cley);
		vley.setVisible();
	}

}