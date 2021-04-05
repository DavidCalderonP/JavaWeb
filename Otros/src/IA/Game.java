package Busquedas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Juego {

	public static void main(String[] args) {
		int[][] estadoincial = {
				{1,2,3},
				{4,5,6},
				{7,8,0}
		};

		int[][] estadofinal = {
				{1,2,3},
				{0,4,5},
				{7,8,6}
		};
		/*
		amplitud = 1
		profundidad = 2
		 */
		Nodo inicial = new Nodo(estadoincial);
		//Nodo resuelto = buscarSolucionPorAmplitud(inicial, estadofinal);
		//Nodo resuelto = buscarSolucionPorProfundidad(inicial, estadofinal);
		Nodo resuelto = buscarSolucionPorAEstrella(inicial, estadofinal);

		verSolucion(resuelto);
		imprimirEstado(estadoincial);
	}
	private static void verSolucion(Nodo solucion) {
		while(solucion.padre!=null) {
			imprimirEstado(solucion.getEstado());
			System.out.println("------------------------");
			solucion = solucion.padre;
		}

	}
	//abiertos.add(0, hijo); //Esta linea es para hacer recorrido en profundidad

	public static Nodo buscarSolucionPorAmplitud(Nodo inicio, int[][] solucion) {
		ArrayList<Nodo> abiertos = new ArrayList<Nodo>();
		abiertos.add(inicio);
		int cont = 0;
		ArrayList<Nodo> visitados = new ArrayList<Nodo>();
		while(abiertos.size()!=0) {
			System.out.println("Visitados");
			System.out.println("#################################");
			for(Nodo n : visitados) {
				imprimirEstado(n.getEstado());
				System.out.println("Costo de este nodo: " + n.getCosto());
				System.out.println("---------------");
			}
			System.out.println("#################################");
			Nodo revisar = abiertos.remove(0);
			revisar.setCosto(calcularCosto(revisar.getEstado(), solucion));
			imprimirEstado(revisar.getEstado());
			int[]  pcero = ubicarPosicionCero(revisar.getEstado());
			System.out.println("Iteracion # " + ++cont);
			if(Arrays.deepEquals(revisar.getEstado(), solucion)) {
				System.out.println("***** SOLUCION ENCONTRADA *****");
				return revisar;

			}

			ArrayList<Nodo> hijos = new ArrayList<Nodo>();
			visitados.add(revisar);
			if(pcero[0]!=0) {
				Nodo hijo = new Nodo(clonar(revisar.getEstado()));
				hijo.setCosto(calcularCosto(hijo.getEstado(), solucion));
				int arriba = hijo.getEstado()[pcero[0]-1][pcero[1]];
				hijo.getEstado()[pcero[0]][pcero[1]] = arriba;
				hijo.getEstado()[pcero[0]-1][pcero[1]] = 0;
				if(!estaEnVisitados(visitados, hijo))
					abiertos.add(hijo); //Esta linea es para hacer recorrido em amplitud

				hijos.add(hijo);
			}
			if(pcero[0]!=2) {
				Nodo hijo = new Nodo(clonar(revisar.getEstado()));
				hijo.setCosto(calcularCosto(hijo.getEstado(), solucion));
				int abajo = hijo.getEstado()[pcero[0]+1][pcero[1]];
				hijo.getEstado()[pcero[0]][pcero[1]] = abajo;
				hijo.getEstado()[pcero[0]+1][pcero[1]] = 0;
				if(!estaEnVisitados(visitados, hijo))
					abiertos.add(hijo); //Esta linea es para hacer recorrido em amplitud

				hijos.add(hijo);
			}
			if(pcero[1]!=0) {
				Nodo hijo = new Nodo(clonar(revisar.getEstado()));
				hijo.setCosto(calcularCosto(hijo.getEstado(), solucion));
				int izq = hijo.getEstado()[pcero[0]][pcero[1]-1];
				hijo.getEstado()[pcero[0]][pcero[1]] = izq;
				hijo.getEstado()[pcero[0]][pcero[1]-1] = 0;
				if(!estaEnVisitados(visitados, hijo))
					abiertos.add(hijo); //Esta linea es para hacer recorrido em amplitud

				hijos.add(hijo);
			}
			if(pcero[1]!=2) {
				Nodo hijo = new Nodo(clonar(revisar.getEstado()));
				hijo.setCosto(calcularCosto(hijo.getEstado(), solucion));
				int der = hijo.getEstado()[pcero[0]][pcero[1]+1];
				hijo.getEstado()[pcero[0]][pcero[1]] = der;
				hijo.getEstado()[pcero[0]][pcero[1]+1] = 0;
				if(!estaEnVisitados(visitados, hijo))
					abiertos.add(hijo); //Esta linea es para hacer recorrido em amplitud
				hijos.add(hijo);
			}
			revisar.setHijos(hijos);
		}
		return null;

	}

	public static Nodo buscarSolucionPorProfundidad(Nodo inicio, int[][] solucion) {
		ArrayList<Nodo> abiertos = new ArrayList<Nodo>();
		abiertos.add(inicio);
		int cont = 0;
		ArrayList<Nodo> visitados = new ArrayList<Nodo>();
		while(abiertos.size()!=0) {
			System.out.println("Visitados");
			System.out.println("#################################");
			for(Nodo n : visitados) {
				imprimirEstado(n.getEstado());
				System.out.println("Costo de este nodo: " + n.getCosto());
				System.out.println("---------------");
			}
			System.out.println("#################################");
			Nodo revisar = abiertos.remove(0);
			revisar.setCosto(calcularCosto(revisar.getEstado(), solucion));
			imprimirEstado(revisar.getEstado());
			int[]  pcero = ubicarPosicionCero(revisar.getEstado());
			System.out.println("Iteracion # " + ++cont);
			if(Arrays.deepEquals(revisar.getEstado(), solucion)) {
				System.out.println("***** SOLUCION ENCONTRADA *****");
				return revisar;

			}

			ArrayList<Nodo> hijos = new ArrayList<Nodo>();
			visitados.add(revisar);
			if(pcero[0]!=0) {
				Nodo hijo = new Nodo(clonar(revisar.getEstado()));
				hijo.setCosto(calcularCosto(hijo.getEstado(), solucion));
				int arriba = hijo.getEstado()[pcero[0]-1][pcero[1]];
				hijo.getEstado()[pcero[0]][pcero[1]] = arriba;
				hijo.getEstado()[pcero[0]-1][pcero[1]] = 0;
				if(!estaEnVisitados(visitados, hijo))
					abiertos.add(0, hijo); //Esta linea es para hacer recorrido en profundidad

				hijos.add(hijo);
			}
			if(pcero[0]!=2) {
				Nodo hijo = new Nodo(clonar(revisar.getEstado()));
				hijo.setCosto(calcularCosto(hijo.getEstado(), solucion));
				int abajo = hijo.getEstado()[pcero[0]+1][pcero[1]];
				hijo.getEstado()[pcero[0]][pcero[1]] = abajo;
				hijo.getEstado()[pcero[0]+1][pcero[1]] = 0;
				if(!estaEnVisitados(visitados, hijo))
					abiertos.add(0, hijo); //Esta linea es para hacer recorrido en profundidad

				hijos.add(hijo);
			}
			if(pcero[1]!=0) {
				Nodo hijo = new Nodo(clonar(revisar.getEstado()));
				hijo.setCosto(calcularCosto(hijo.getEstado(), solucion));
				int izq = hijo.getEstado()[pcero[0]][pcero[1]-1];
				hijo.getEstado()[pcero[0]][pcero[1]] = izq;
				hijo.getEstado()[pcero[0]][pcero[1]-1] = 0;
				if(!estaEnVisitados(visitados, hijo))
					abiertos.add(0, hijo); //Esta linea es para hacer recorrido en profundidad

				hijos.add(hijo);
			}
			if(pcero[1]!=2) {
				Nodo hijo = new Nodo(clonar(revisar.getEstado()));
				hijo.setCosto(calcularCosto(hijo.getEstado(), solucion));
				int der = hijo.getEstado()[pcero[0]][pcero[1]+1];
				hijo.getEstado()[pcero[0]][pcero[1]] = der;
				hijo.getEstado()[pcero[0]][pcero[1]+1] = 0;
				if(!estaEnVisitados(visitados, hijo))
					abiertos.add(0, hijo); //Esta linea es para hacer recorrido en profundidad
				hijos.add(hijo);
			}
			revisar.setHijos(hijos);
		}
		return null;

	}

	public static Nodo buscarSolucionPorAEstrella(Nodo inicio, int[][] solucion){
		ArrayList<Nodo> abiertos = new ArrayList<Nodo>();
		abiertos.add(inicio);
		int cont = 0;
		ArrayList<Nodo> visitados = new ArrayList<Nodo>();
		while(abiertos.size()!=0) {
			System.out.println("Visitados");
			System.out.println("#################################");
			for(Nodo n : visitados) {
				imprimirEstado(n.getEstado());
				System.out.println("Costo de este nodo: " + n.getCosto());
				System.out.println("---------------");
			}
			System.out.println("#################################");
			Nodo revisar = abiertos.remove(0);
			revisar.setCosto(calcularCosto(revisar.getEstado(), solucion));
			imprimirEstado(revisar.getEstado());
			int[]  pcero = ubicarPosicionCero(revisar.getEstado());
			System.out.println("Iteracion # " + ++cont);
			if(Arrays.deepEquals(revisar.getEstado(), solucion)) {
				System.out.println("***** SOLUCION ENCONTRADA *****");
				return revisar;

			}

			ArrayList<Nodo> hijos = new ArrayList<Nodo>();
			ArrayList<Nodo> auxabiertos = new ArrayList<Nodo>();
			visitados.add(revisar);
			if(pcero[0]!=0) {
				Nodo hijo = new Nodo(clonar(revisar.getEstado()));
				//hijo.setCosto(calcularCosto(hijo.getEstado(), solucion));
				int arriba = hijo.getEstado()[pcero[0]-1][pcero[1]];
				hijo.getEstado()[pcero[0]][pcero[1]] = arriba;
				hijo.getEstado()[pcero[0]-1][pcero[1]] = 0;
				if(!estaEnVisitados(visitados, hijo)) {
					auxabiertos.add(hijo);
				}
				//abiertos.add(hijo); //Esta linea es para hacer recorrido em amplitud

				hijos.add(hijo);
			}
			if(pcero[0]!=2) {
				Nodo hijo = new Nodo(clonar(revisar.getEstado()));
				//hijo.setCosto(calcularCosto(hijo.getEstado(), solucion));
				int abajo = hijo.getEstado()[pcero[0]+1][pcero[1]];
				hijo.getEstado()[pcero[0]][pcero[1]] = abajo;
				hijo.getEstado()[pcero[0]+1][pcero[1]] = 0;
				if(!estaEnVisitados(visitados, hijo)) {
					auxabiertos.add(hijo);
				}
				//abiertos.add(hijo); //Esta linea es para hacer recorrido em amplitud

				hijos.add(hijo);
			}
			if(pcero[1]!=0) {
				Nodo hijo = new Nodo(clonar(revisar.getEstado()));
				//hijo.setCosto(calcularCosto(hijo.getEstado(), solucion));
				int izq = hijo.getEstado()[pcero[0]][pcero[1]-1];
				hijo.getEstado()[pcero[0]][pcero[1]] = izq;
				hijo.getEstado()[pcero[0]][pcero[1]-1] = 0;
				if(!estaEnVisitados(visitados, hijo)) {
					auxabiertos.add(hijo);
				}
				//abiertos.add(hijo); //Esta linea es para hacer recorrido em amplitud

				hijos.add(hijo);
			}
			if(pcero[1]!=2) {
				Nodo hijo = new Nodo(clonar(revisar.getEstado()));
				//hijo.setCosto(calcularCosto(hijo.getEstado(), solucion));
				int der = hijo.getEstado()[pcero[0]][pcero[1]+1];
				hijo.getEstado()[pcero[0]][pcero[1]] = der;
				hijo.getEstado()[pcero[0]][pcero[1]+1] = 0;
				if(!estaEnVisitados(visitados, hijo)) {

					auxabiertos.add(hijo);
				}
				//abiertos.add(hijo); //Esta linea es para hacer recorrido em amplitud
				hijos.add(hijo);
			}
			//Collections.sort(hijos, (x, y) -> x.getCosto() - y.getCosto());

			System.out.println("IMPRIMIENDO LOS AUXILIARES");
			for(Nodo nodo : auxabiertos) {
				imprimirEstado(nodo.getEstado());
				nodo.setCosto(calcularCosto(nodo.getEstado(), solucion));
				System.out.println(nodo.getCosto());
			}
			Collections.sort(auxabiertos, (x, y) -> x.getCosto() - y.getCosto());
			abiertos.add(auxabiertos.remove(0));
			auxabiertos.clear();
			revisar.setHijos(hijos);
		}
		return null;
	}


	private static int calcularCosto(int[][] estado, int[][] solucion) {
		int match = 0;
		for(int i = 0 ; i < estado.length ; i++) {
			for(int j = 0 ; j < estado.length ; j++ ) {
				if(estado[i][j]!=solucion[i][j])
					match++;
			}
		}
		return match;
	}

	private static boolean estaEnVisitados(ArrayList<Nodo> visitados, Nodo hijo) {
		for(Nodo n : visitados) {
			if(Arrays.deepEquals(n.getEstado(), hijo.getEstado()))
				return true;
		}
		return false;
	}

	private static int[][] clonar(int[][] estado) {
		int[][] clon = new int[estado.length][estado.length]; 
		for(int i  = 0 ; i < estado.length ; i++) {
			for(int j  = 0 ; j < estado.length ; j++) {
				clon[i][j] = estado[i][j];
			}
		}
		return clon;
	}

	private static int[] ubicarPosicionCero(int[][] estado) {
		//int[] posicion = new int[2];
		for(int i  = 0 ; i < estado.length ; i++) {
			for(int j  = 0 ; j < estado.length ; j++) {
				if(estado[i][j]==0) {
					System.out.println("La posicion del espacio cero es: "+ i +","+j);
					return new int[] {i,j};
				}
				//System.out.println("La posicion del espacio cero es: "+ i +","+j);
				//posicion[0]= i;
				//posicion[1]=j;
			}
		}
		return null;
	}

	public static void imprimirEstado(int[][] estado) {
		for(int i  = 0 ; i < estado.length ; i++) {
			for(int j  = 0 ; j < estado.length ; j++) {
				System.out.print("["+estado[i][j]+"]");
			}
			System.out.println();
		}
	}

}
