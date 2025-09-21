package Ordenamiento;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// Casos típicos para probar el algoritmo
		List<List<Integer>> casos = Arrays.asList(Collections.emptyList(), // 1. Vacío
				Arrays.asList(42), // 2. Un solo elemento
				Arrays.asList(1, 2, 3, 4, 5), // 3. Ya ordenado
				Arrays.asList(5, 4, 3, 2, 1), // 4. Inverso
				Arrays.asList(4, 2, 4, 3, 2), // 5. Repetidos
				Arrays.asList(-3, 7, -1, 5, 0), // 6. Negativos y positivos
				Arrays.asList(9, 9, 9, 9), // 7. Todos iguales
				Arrays.asList(7, 3, 1, 8, 2, 5, 4, 6) // 8. Aleatorio
		);

		int casoNum = 1;
		for (List<Integer> caso : casos) {
			System.out.print("Caso " + casoNum + ": " + caso + " -> ");
			Ordenamiento.patienceSort(caso);
			System.out.println(caso);
			casoNum++;
		}
	}
}
