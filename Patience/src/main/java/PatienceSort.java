import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PatienceSort {
    public static class SortResult {
        public long time;
        public long iterations;
    }

    public static SortResult patienceSort(int[] arr) {
        SortResult result = new SortResult();
        long startTime = System.currentTimeMillis();
        long iterations = 0;

        if (arr == null || arr.length == 0) {
            result.time = 0;
            result.iterations = 0;
            return result;
        }

        List<List<Integer>> piles = new ArrayList<>();

        // Первый этап: раскладываем элементы в стопки
        for (int num : arr) {
            boolean placed = false;
            for (List<Integer> pile : piles) {
                iterations++;
                if (pile.get(pile.size() - 1) >= num) {
                    pile.add(num);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                List<Integer> newPile = new ArrayList<>();
                newPile.add(num);
                piles.add(newPile);
            }
        }

        // Второй этап: извлекаем минимальные элементы из стопок
        for (int i = 0; i < arr.length; i++) {
            // Находим стопку с минимальным верхним элементом
            List<Integer> minPile = null;
            int minIndex = -1;

            for (int j = 0; j < piles.size(); j++) {
                iterations++;
                List<Integer> pile = piles.get(j);
                if (minPile == null ||
                        pile.get(pile.size() - 1) < minPile.get(minPile.size() - 1)) {
                    minPile = pile;
                    minIndex = j;
                }
            }

            // Извлекаем элемент из найденной стопки
            arr[i] = minPile.remove(minPile.size() - 1);

            // Если стопка пуста, удаляем ее
            if (minPile.isEmpty()) {
                piles.remove(minIndex);
            }
        }

        result.time = System.currentTimeMillis() - startTime;
        result.iterations = iterations;
        return result;
    }

    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 10);
        }
        return arr;
    }
}