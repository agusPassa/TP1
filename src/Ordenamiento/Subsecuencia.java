package Ordenamiento;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;


public class Subsecuencia {

	class Nodo<T> {
		T valor;
		Nodo<T> predecesor;

		Nodo(T valor, Nodo<T> predecesor) {
			this.valor = valor;
			this.predecesor = predecesor;
		}
	}

	public <T extends Comparable<T>> List<Stack<Nodo<T>>> apilar(List<T> vec) {
		if (vec.isEmpty())
			return null;
		List<Stack<Nodo<T>>> resultado = new ArrayList<Stack<Nodo<T>>>(1);
		ArrayList<T> topes = new ArrayList<T>();
		// Sobre este ArrayList vamos a hacer bsearch aprovechando que está por
		// definición ordenado de menor a mayor

		for (T elem : vec) {
			int pos = this.busquedaBinariaMod(topes, elem);
			if (pos > resultado.size() - 1) {
				resultado.add(new Stack<Nodo<T>>());
				topes.add(elem);
			} else {
				topes.set(pos, elem);
			}
			Nodo<T> aux = new Nodo<T>(elem, (pos == 0) ? null : resultado.get(pos - 1).lastElement());
			resultado.get(pos).add(aux);
		}
		return resultado;
	}

	public <T extends Comparable<T>> List<T> obtenerLIS(List<T> vec) {
		List<T> res = this.obtenerLISConPilas(this.apilar(vec));
		return res == null ? vec : res;
	}

	private <T extends Comparable<T>> List<T> obtenerLISConPilas(List<Stack<Nodo<T>>> pilas) {
		if (pilas == null)
			return null;
		List<T> res = new ArrayList<T>(1);
		Nodo<T> ultimo = pilas.getLast().getLast();
		while (ultimo.predecesor != null) {
			res.add(ultimo.valor);
			ultimo = ultimo.predecesor;
		}
		res.add(ultimo.valor);
		Collections.reverse(res);
		return res;
	}

	// Esta busqueda retorna la posicion final donde debe ser insertado el elemento
	// N
	// Lo dejo en publico para testearlo, la idea final seria que sea privado
	public <T extends Comparable<T>> int busquedaBinariaMod(ArrayList<T> vec, T elem) {
		int res = Collections.binarySearch(vec, elem);
		return (res < 0) ? (res * (-1) - 1) : res;
//		la bsearch por defecto devuelve el indice en el que está el elemento si
//		lo encuentra. Sino devuelve la posición en la que se ubicaría pero negativa
//		y -1. Ejemplo: Vector [4, 6, 8, 10, 11]
//		bsearch(4) = 0
//		bsearch(7) = -3 (iría en la posición 2, por lo que hace -2 - 1 = -3)
//		bsearch(2) = -1
//		bsearch(20)= -6 
//		Con la corrección del resultado me dice exactamente donde iría el elemento
//		a insertar
	}
}
