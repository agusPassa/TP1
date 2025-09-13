package Ordenamiento;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Ordenamiento {
	public ArrayList<LinkedList<Integer>> apilar(LinkedList<Integer> vec)
	{
		// 10 11 12 13 1
		ArrayList<LinkedList<Integer>> resultado = new ArrayList<LinkedList<Integer>>();
		//Primer elemento, se crea la linkedList y le metemos el primer elemento de vec
		resultado.add(new LinkedList<Integer>());
		ArrayList<Integer> topes = new ArrayList<Integer>();
		int primero=vec.poll();
		resultado.get(0).add(primero);
		topes.add(primero);
		for (Integer elem : vec) {
			int pos=this.busquedaBinariaMod(topes, elem);
			if(pos>resultado.size()-1)
			{
				resultado.add(new LinkedList<Integer>());
				topes.add(elem);
			}
			else
			{				
				topes.set(pos, elem);
			}
			resultado.get(pos).add(elem);
		}
		vec.addFirst(primero);
		return resultado;
	}
	
	public ArrayList<Integer> ordenarPilas(ArrayList<LinkedList<Integer>> pilas)
	{
		System.out.println("Inicio de ordenarPilas" + pilas);
		int cont=0;
		int tam= pilas.size();
		PriorityQueue<Integer> cola = new PriorityQueue<Integer>();
		ArrayList<Integer> ordenada = new ArrayList<Integer>();
		Map<Integer, Integer> tuplaTopes = new HashMap<Integer, Integer>();
		//Carga inicial de topes
		for (LinkedList<Integer> lista : pilas) {
			int numero=lista.getLast();
			cola.add(numero);
			tuplaTopes.put(numero, cont);
			cont++;
		}
		System.out.println("Cola de prioridad: "+ cola);
		System.out.println("Cantidad de pilas "+ pilas.size());
		while(tam>0)
		{
			int numero=cola.remove();
			System.out.println("De la cola de prioridad saco " + numero);
			ordenada.add(numero);
			int pos = tuplaTopes.get(numero);
			pilas.get(pos).removeLast();
			if(pilas.get(pos).isEmpty())
			{
				tam--;
				tuplaTopes.remove(numero);
			}
			else
			{
				numero=pilas.get(pos).getLast();
				cola.add(numero);
				tuplaTopes.put(numero, pos);
			}			
		}
		return ordenada;
	}
	public ArrayList<Integer> ordenarNumeros(LinkedList<Integer> vec) {
		return this.ordenarPilas(this.apilar(vec));
	}	
	//Esta busqueda retorna la posicion final donde debe ser insertado el elemento N
	//Lo dejo en publico para testearlo, la idea final seria que sea privado
	public int busquedaBinariaMod(ArrayList<Integer> vec, int elem)
	{
		int res = Collections.binarySearch(vec, elem);
		return (res < 0)?(res * (-1) - 1):res;
	}
}
