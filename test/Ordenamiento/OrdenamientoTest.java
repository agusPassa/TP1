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

    @Test
    void ordenaListaVacia() {
        LinkedList<Punto> prueba = new LinkedList<>();
        LinkedList<Punto> esperado = new LinkedList<>();

        Ordenamiento.patienceSort(prueba);
        assertEquals(esperado, prueba);
    }

    @Test
    void ordenaUnicoElemento() {
        LinkedList<Punto> prueba = new LinkedList<>();
        prueba.add(new Punto(3, 4));

        LinkedList<Punto> esperado = new LinkedList<>();
        esperado.add(new Punto(3, 4));

        Ordenamiento.patienceSort(prueba);
        assertEquals(esperado, prueba);
    }

    @Test
    void ordenaListaOrdenado() {
        LinkedList<Punto> prueba = new LinkedList<>();
        prueba.add(new Punto(3, 4));
        prueba.add(new Punto(9, 2));
        prueba.add(new Punto(15, 8));
        prueba.add(new Punto(16, -2));

        LinkedList<Punto> esperado = new LinkedList<>();
        esperado.add(new Punto(3, 4));
        esperado.add(new Punto(9, 2));
        esperado.add(new Punto(15, 8));
        esperado.add(new Punto(16, -2));

        Ordenamiento.patienceSort(prueba);
        assertEquals(esperado, prueba);
    }

    @Test
    void ordenaListaEnOrdenInv() {
        LinkedList<Punto> prueba = new LinkedList<>();
        prueba.add(new Punto(16, -2));
        prueba.add(new Punto(15, 8));
        prueba.add(new Punto(9, 2));
        prueba.add(new Punto(3, 4));

        LinkedList<Punto> esperado = new LinkedList<>();
        esperado.add(new Punto(3, 4));
        esperado.add(new Punto(9, 2));
        esperado.add(new Punto(15, 8));
        esperado.add(new Punto(16, -2));

        Ordenamiento.patienceSort(prueba);
        assertEquals(esperado, prueba);
    }

    @Test
    void ordenaListaConRepetidos() {
        LinkedList<Punto> prueba = new LinkedList<>();
        prueba.add(new Punto(16, -2));
        prueba.add(new Punto(3, 4));
        prueba.add(new Punto(15, -4));
        prueba.add(new Punto(3, 4));
        prueba.add(new Punto(16, -2));
        prueba.add(new Punto(15, -4));
        prueba.add(new Punto(9, 2));

        LinkedList<Punto> esperado = new LinkedList<>();
        esperado.add(new Punto(3, 4));
        esperado.add(new Punto(3, 4));
        esperado.add(new Punto(9, 2));
        esperado.add(new Punto(15, -4));
        esperado.add(new Punto(15, -4));
        esperado.add(new Punto(16, -2));
        esperado.add(new Punto(16, -2));

        Ordenamiento.patienceSort(prueba);
        assertEquals(esperado, prueba);
    }

    @Test
    void ordenaListaConTodosIguales() {
        LinkedList<Punto> prueba = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            prueba.add(new Punto(16, 2));
        }

        LinkedList<Punto> esperado = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            esperado.add(new Punto(16, 2));
        }

        Ordenamiento.patienceSort(prueba);
        assertEquals(esperado, prueba);
    }

    @Test
    void ordenaListaConValoresExtremos() {
        LinkedList<Punto> prueba = new LinkedList<>();
        prueba.add(new Punto(6, Integer.MIN_VALUE));
        prueba.add(new Punto(Integer.MAX_VALUE, 1));
        prueba.add(new Punto(1653428, Integer.MAX_VALUE));
        prueba.add(new Punto(Integer.MIN_VALUE, -1));
        prueba.add(new Punto(1653428, 3));
        prueba.add(new Punto(-8436316, 2));
        prueba.add(new Punto(6, 0));

        LinkedList<Punto> esperado = new LinkedList<>();
        esperado.add(new Punto(Integer.MIN_VALUE, -1));
        esperado.add(new Punto(-8436316, 2));
        esperado.add(new Punto(6, Integer.MIN_VALUE));
        esperado.add(new Punto(6, 0));
        esperado.add(new Punto(1653428, 3));
        esperado.add(new Punto(1653428, Integer.MAX_VALUE));
        esperado.add(new Punto(Integer.MAX_VALUE, 1));

        Ordenamiento.patienceSort(prueba);
        assertEquals(esperado, prueba);
    }

    @Test
    void testOrdenamientoChico() {
        LinkedList<Integer> obtenido = new LinkedList<>();
        obtenido.add(10);
        obtenido.add(11);
        obtenido.add(1);
        obtenido.add(12);
        obtenido.add(13);

        LinkedList<Integer> esperado = new LinkedList<>();
        esperado.add(1);
        esperado.add(10);
        esperado.add(11);
        esperado.add(12);
        esperado.add(13);

        Ordenamiento.patienceSort(obtenido);
        assertEquals(esperado, obtenido);
    }

    @Test
    void testEstabilidad_NoEsEstable() {
        LinkedList<Punto> obtenido = new LinkedList<>();
        obtenido.add(new Punto(1, 1));
        obtenido.add(new Punto(2, 1));
        obtenido.add(new Punto(4, 1));
        obtenido.add(new Punto(2, 3));
        obtenido.add(new Punto(6, 1));
        obtenido.add(new Punto(2, 2));

        LinkedList<Punto> esperado = new LinkedList<>();
        esperado.add(new Punto(1, 1));
        esperado.add(new Punto(2, 1));
        esperado.add(new Punto(2, 3));
        esperado.add(new Punto(2, 2));
        esperado.add(new Punto(4, 1));
        esperado.add(new Punto(6, 1));

        Ordenamiento.patienceSort(obtenido);
        assertNotEquals(esperado, obtenido); // porque el algoritmo no es estable
    }

    @Test
    void manejaEntradaNula() {
        LinkedList<Punto> puntos = null;
        assertThrows(NullPointerException.class, () -> Ordenamiento.patienceSort(puntos));
    }
}