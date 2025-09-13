package Ordenamiento;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Objects;

import org.junit.jupiter.api.Test;


class OrdenamientoTest {

	record Punto(int x, int y) implements Comparable<Punto> {
		@Override
		public int compareTo(Punto otro) {
			int cmp = Integer.compare(this.x, otro.x);
			if (cmp != 0)
				return cmp;
			return Integer.compare(this.y, otro.y);
		}
		
		@Override
		public boolean equals(Object o) {
		    if (this == o) return true;
		    if (!(o instanceof Punto)) return false;
		    Punto p = (Punto) o;
		    return x == p.x && y == p.y;
		}

		@Override
		public int hashCode() {
		    return Objects.hash(x, y);
		}
		
	}

	// Un record es como una clase chiquita que viene con varios métodos por
	// defecto.
	// Es util para ahorrar tiempo. Será usada para tests de estabilidad
	static Ordenamiento ord = new Ordenamiento();

	@Test
	void testOrdenamientoChico() {
		LinkedList<Integer> obtenido = new LinkedList<Integer>();
		obtenido.add(10);
		obtenido.add(11);
		obtenido.add(1);
		obtenido.add(12);
		obtenido.add(13);

		LinkedList<Integer> esperado = new LinkedList<Integer>();
		esperado.add(1);
		esperado.add(10);
		esperado.add(11);
		esperado.add(12);
		esperado.add(13);

		assertEquals(esperado, ord.ordenarNumeros(obtenido));
	}

	@Test
	void testEstabilidad_NoEsEstable() {
		LinkedList<Punto> obtenido = new LinkedList<Punto>();
		obtenido.add(new Punto(1, 1));
		obtenido.add(new Punto(2, 1));
		obtenido.add(new Punto(4, 1));
		obtenido.add(new Punto(2, 3));
		obtenido.add(new Punto(6, 1));
		obtenido.add(new Punto(2, 2));
		LinkedList<Punto> esperado = new LinkedList<Punto>();
		esperado.add(new Punto(1, 1));
		esperado.add(new Punto(2, 1));
		esperado.add(new Punto(2, 3));
		esperado.add(new Punto(2, 2));
		esperado.add(new Punto(4, 1));
		esperado.add(new Punto(6, 1));

		assertNotEquals(esperado, ord.ordenarNumeros(obtenido));
	}

	@Test
	void testListaVacia_DevuelveNull() {
		LinkedList<Punto> obtenido = new LinkedList<Punto>();
		assertNull(ord.ordenarNumeros(obtenido));
	}

}
