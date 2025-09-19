package Ordenamiento;

import java.util.LinkedList;

import Ordenamiento.OrdenamientoTest.Punto;

public class Main {

	public static void main(String[] args) {
		Ordenamiento ord = new Ordenamiento();
		LinkedList<Integer> prueba = new LinkedList<Integer>();

		prueba.add(1);
		prueba.add(2);
		prueba.add(3);
		prueba.add(4);
		prueba.add(5);
		prueba.add(5);
		prueba.add(-1);
		prueba.add(8);
		prueba.add(6);
		prueba.add(99);
		prueba.add(7);

		ord.apilar(prueba);
		ord.ordenarNumeros(prueba);
		
		System.out.println(prueba);
		System.out.println(ord.ordenarNumeros(prueba));
		System.out.println("Longest Increasing Subsequence: "+ord.obtenerLIS(prueba));


	}

}
