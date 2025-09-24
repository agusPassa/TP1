package Ordenamiento;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Casos típicos para probar el algoritmo con Elemento (clave, etiqueta)
        List<List<Elemento>> casos = Arrays.asList(
                Collections.emptyList(), 			  // 1. Vacío
                Arrays.asList(new Elemento(42, "A")), // 2. Un solo elemento
                Arrays.asList( 						  // 3. Ya ordenado
                        new Elemento(1, "A"),
                        new Elemento(2, "B"),
                        new Elemento(3, "C"),
                        new Elemento(4, "D"),
                        new Elemento(5, "E")),
                Arrays.asList( 						  // 4. Inverso
                        new Elemento(5, "A"),
                        new Elemento(4, "B"),
                        new Elemento(3, "C"),
                        new Elemento(2, "D"),
                        new Elemento(1, "E")),
                Arrays.asList( 						  // 5. Repetidos
                        new Elemento(4, "A"),
                        new Elemento(2, "B"),
                        new Elemento(4, "C"),
                        new Elemento(3, "D"),
                        new Elemento(2, "E")),
                Arrays.asList( 						  // 6. Negativos y positivos
                        new Elemento(-3, "A"),
                        new Elemento(7, "B"),
                        new Elemento(-1, "C"),
                        new Elemento(5, "D"),
                        new Elemento(0, "E")),
                Arrays.asList( 						  // 7. Todos iguales
                        new Elemento(9, "A"),
                        new Elemento(9, "B"),
                        new Elemento(9, "C"),
                        new Elemento(9, "D")),
                Arrays.asList( 						  // 8. Aleatorio
                        new Elemento(7, "A"),
                        new Elemento(3, "B"),
                        new Elemento(1, "C"),
                        new Elemento(8, "D"),
                        new Elemento(2, "E"),
                        new Elemento(5, "F"),
                        new Elemento(4, "G"),
                        new Elemento(6, "H"))
        );

        int casoNum = 1;
        for (List<Elemento> caso : casos) {
            System.out.print("Caso " + casoNum + ": " + caso + "  -->  ");
            Ordenamiento.patienceSort(caso); // debe aceptar Comparable
            System.out.println(caso);
            casoNum++;
        }
    }
}
