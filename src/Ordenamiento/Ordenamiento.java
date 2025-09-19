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
	
	class Nodo<T> {
	    T valor;
	    Nodo<T> predecesor;

	    Nodo(T valor, Nodo<T> predecesor) {
	        this.valor = valor;
	        this.predecesor = predecesor;
	    }
	}
	
	public <T extends Comparable<T>> List<Stack<Nodo<T>>> apilar(List<T> vec)
	{
		if(vec.isEmpty())
			return null;
		List<Stack<Nodo<T>>> resultado = new ArrayList<Stack<Nodo<T>>>(1);
		ArrayList<T> topes = new ArrayList<T>();
		//Sobre este ArrayList vamos a hacer bsearch aprovechando que está por
		//definición ordenado de menor a mayor

		for(T elem: vec) {
			int pos=this.busquedaBinariaMod(topes, elem);
			if(pos>resultado.size()-1)
			{
				resultado.add(new Stack<Nodo<T>>());
				topes.add(elem);
			}
			else
			{				
				topes.set(pos, elem);
			}
			Nodo<T> aux = new Nodo<T>(elem, (pos==0)?null:resultado.get(pos-1).lastElement());
			resultado.get(pos).add(aux);
		}
		return resultado;
	}
	

	
	public <T extends Comparable<T>> List<T> ordenarPilas(List<Stack<Nodo<T>>> pilas)
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
		for (Stack<Nodo<T>> pila : pilas) {
			Nodo<T> elem=pila.getLast();
			cola.add(elem.valor);
			tuplaTopes.put(elem.valor, cont);
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
						Stack<Nodo<T>> pila = pilas.get(i);
						if(!pila.isEmpty() && pila.lastElement().valor.equals(elem))
							tuplaTopes.put(elem, i);
					}
				}
			}
			else
			{
				elem=pilas.get(pos).getLast().valor;
				cola.add(elem);
				tuplaTopes.put(elem, pos);
			}			
		}
		return ordenada;
	}
	
	private <T extends Comparable<T>> List<T> obtenerLISConPilas(List<Stack<Nodo<T>>> pilas)
	{
		if(pilas == null)
			return null;
		List<T> res = new ArrayList<T>(1);
		Nodo<T> ultimo= pilas.getLast().getLast();
		while(ultimo.predecesor != null)
		{
			res.add(ultimo.valor);
			ultimo=ultimo.predecesor;
		}
		res.add(ultimo.valor);
		Collections.reverse(res);
		return res;
	}
	
	public <T extends Comparable<T>> List<T> ordenarNumeros(List<T> vec) {
		List<T> res = this.ordenarPilas(this.apilar(vec));
		return res == null ? vec : res;
	}	
	
	public <T extends Comparable<T>> List<T> obtenerLIS(List<T> vec) {
		List<T> res = this.obtenerLISConPilas(this.apilar(vec));
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
