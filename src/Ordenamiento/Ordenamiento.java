package Ordenamiento;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Ordenamiento {

	public static <T extends Comparable<T>> void patienceSort(List<T> input) {
	    // ---- FASE 1: Construcción de las pilas ----
	    List<Stack<T>> pilas = apilar(input);

	    // ---- FASE 2: Merge con cola de prioridad ----
	    // La cola guarda (valor del tope, índice de pila)
	    mezclarPilas(input, pilas);
	}

	private static <T extends Comparable<T>> void mezclarPilas(List<T> array, List<Stack<T>> pilas) {
		PriorityQueue<Nodo<T>> colaTopes = new PriorityQueue<>();
	    for (int i = 0; i < pilas.size(); i++) {
	        Stack<T> pila = pilas.get(i);
	        colaTopes.offer(new Nodo<>(pila.pop(), i));
	    }

	    int i = 0;
	    while (!colaTopes.isEmpty()) {
	    	Nodo<T> nodo = colaTopes.poll(); // Extraer mínimo
	    	array.set(i, nodo.valorTope);

	        // Si la pila de donde salió aún tiene elementos, meter el nuevo tope
	        Stack<T> pila = pilas.get(nodo.indicePila);
	        if (!pila.isEmpty()) {
	            colaTopes.offer(new Nodo<>(pila.pop(), nodo.indicePila));
	        }
	        i++;
	    }
	}

	private static <T extends Comparable<T>> List<Stack<T>> apilar(List<T> input) {
		List<Stack<T>> pilas = new ArrayList<>();

	    for (T x : input) {
	        // Búsqueda binaria para encontrar la primera pila cuyo tope >= x
	        int i = binarySearch(pilas, x);
	        if (i == pilas.size()) {
	            // No se encontró -> crear nueva pila
	            pilas.add(new Stack<>());
	        }
	        pilas.get(i).push(x);
	    }
		return pilas;
	}

	// Búsqueda binaria sobre los topes de las pilas
	private static <T extends Comparable<T>> int binarySearch(List<Stack<T>> pilas, T x) {
	    int izq = 0, der = pilas.size();
	    while (izq < der) {
	        int medio = (izq + der) / 2;
	        if (pilas.get(medio).peek().compareTo(x) >= 0) {
	            der = medio;
	        } else {
	            izq = medio + 1;
	        }
	    }
	    return izq;
	}

	// Clase auxiliar para guardar tope + pila
	private static class Nodo<T extends Comparable<T>> implements Comparable<Nodo<T>> {
	    T valorTope;
	    int indicePila;

	    Nodo(T valorTope, int indicePila) {
	        this.valorTope = valorTope;
	        this.indicePila = indicePila;
	    }

	    @Override
	    public int compareTo(Nodo<T> otro) {
	        return this.valorTope.compareTo(otro.valorTope);
	    }
	}
}
