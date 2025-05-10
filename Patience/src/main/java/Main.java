import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileWriter csvWriter = new FileWriter("results.csv");
            csvWriter.append("Size,Time(ms),Iterations\n");

            // Генерируем 100 наборов данных от 100 до 10000 элементов
            for (int i = 1; i <= 100; i++) {
                int size = 100 * i;
                int[] arr = PatienceSort.generateRandomArray(size);

                // Копируем массив для чистоты эксперимента
                int[] arrCopy = new int[arr.length];
                System.arraycopy(arr, 0, arrCopy, 0, arr.length);

                PatienceSort.SortResult result = PatienceSort.patienceSort(arrCopy);

                // Записываем результаты в CSV
                csvWriter.append(size + "," + result.time + "," + result.iterations + "\n");
                System.out.printf("Size: %6d | Time: %4d ms | Iterations: %10d\n",
                        size, result.time, result.iterations);
            }

            csvWriter.flush();
            csvWriter.close();
            System.out.println("Результаты сохранены в results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}