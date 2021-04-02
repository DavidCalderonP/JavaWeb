package Pruebas;

import java.util.ArrayList;

public class UnshiftArrayList {

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<String>();
		list.add("Hola");
		list.add("Como");
		list.add("Estas");
		list.add(0, "¿");
		list.add("?");
		for(String s : list)
			System.out.println(s);
		

	}

}
