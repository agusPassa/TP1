package Ordenamiento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class main {

	public static void main(String[] args) {
		Ordenamiento ord = new Ordenamiento();
		LinkedList<Integer> prueba = new LinkedList<Integer>();

		prueba.add(10);
		prueba.add(11);
		prueba.add(12);
		prueba.add(13);
		prueba.add(1);

		System.out.println(ord.apilar(prueba));
		System.out.println(ord.ordenarNumeros(prueba));


	}

}
