import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SumOfNaturalNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        scanner.close();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> futureResult = executor.submit(() -> {
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += i;
            }
            return sum;
        });

        try {
            int result = futureResult.get();
            System.out.println("Sum of natural numbers till " + n + " is: " + result);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        executor.shutdown();
    }
}
