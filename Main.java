
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Solution solution = new Solution();
        HashMap<String, Long> minTripTime = solution.minTripTime();
        for(Map.Entry<String, Long> entry : minTripTime.entrySet()) {
            System.out.println("Перевозчик: " + entry.getKey());
            System.out.println("Время в пути: " + entry.getValue() / 60 + " (ч) " + entry.getValue() % 60 + " (м)");
        }
        System.out.println("Разница между средним значением и медианой для цены полета: " + solution.differenceAvgMedian().toString());
    }
}
