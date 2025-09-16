package Ordenamiento;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Collections;
import java.util.HashMap;

public class Ordenamiento {

	public <T extends Comparable<T>> List<Stack<T>> apilar(List<T> vec)
	{
		if(vec.isEmpty())
			return null;
		List<Stack<T>> resultado = new ArrayList<Stack<T>>();
		//Primer elemento, se crea la pila y le metemos el primer elemento de vec
		resultado.add(new Stack<T>());
		ArrayList<T> topes = new ArrayList<T>();
		//Sobre este ArrayList vamos a hacer bsearch aprovechando que está por
		//definición ordenado de menor a mayor
		resultado.get(0).add(vec.getFirst());
		topes.add(vec.getFirst());
		for(int i=1; i<=vec.size()-1; i++) {
			T elem = vec.get(i);
			int pos=this.busquedaBinariaMod(topes, elem);
			if(pos>resultado.size()-1)
			{
				resultado.add(new Stack<T>());
				topes.add(elem);
			}
			else
			{				
				topes.set(pos, elem);
			}
			resultado.get(pos).add(elem);
		}
		return resultado;
	}
	
	public <T extends Comparable<T>> List<T> ordenarPilas(List<Stack<T>> pilas)
	{
		if(pilas == null)
			return null;
		
		int cont=0;
		int tam= pilas.size();
		PriorityQueue<T> cola = new PriorityQueue<T>();
		//Usamos cola de prioridad porque encuentra el minimo en log n
		ArrayList<T> ordenada = new ArrayList<T>();
		Map<T, Integer> tuplaTopes = new HashMap<T, Integer>();
		//Carga inicial de topes
		for (Stack<T> pila : pilas) {
			T elem=pila.getLast();
			cola.add(elem);
			tuplaTopes.put(elem, cont);
			cont++;
		}
		
		while(tam>0)
		{
			T elem=cola.remove();
			ordenada.add(elem);
			int pos = tuplaTopes.get(elem);
			pilas.get(pos).removeLast();
			if(pilas.get(pos).isEmpty())
			{
				tam--; 
				//No puedo sacar una pila porque cambiaría los indices internos
				//de la lista de pilas. Pos no se vuelve a repetir
				
				//Si el elemento que acabamos de sacar es tope de otra pila hay que actualizar el mapa
				if(elem.equals(cola.peek())) 
				{
					for(int i = 0; i < pilas.size(); i++) {
						Stack<T> pila = pilas.get(i);
						if(!pila.isEmpty() && pila.lastElement().equals(elem))
							tuplaTopes.put(elem, i);
					}
				}
			}
			else
			{
				elem=pilas.get(pos).getLast();
				cola.add(elem);
				tuplaTopes.put(elem, pos);
			}			
		}
		return ordenada;
	}
	public <T extends Comparable<T>> List<T> ordenarNumeros(List<T> vec) {
		List<T> res = this.ordenarPilas(this.apilar(vec));
		return res == null ? vec : res;
	}	
	//Esta busqueda retorna la posicion final donde debe ser insertado el elemento N
	//Lo dejo en publico para testearlo, la idea final seria que sea privado
	public <T extends Comparable<T>> int busquedaBinariaMod(ArrayList<T> vec, T elem)
	{
		int res = Collections.binarySearch(vec, elem);		
		return (res < 0)?(res * (-1) - 1):res;
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
