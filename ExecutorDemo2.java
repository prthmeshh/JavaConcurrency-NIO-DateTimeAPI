import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo2 {
    public static void main(String[] args) {

        Runnable runnableTask = () -> {
            // Task 1 behavior
            System.out.println("Task 1 - Current Time :: " + LocalDateTime.now());

            // Task 2 behavior
            System.out.println("Task 2 - Current Time :: " + LocalDateTime.now());

            // Task 3 behavior
            System.out.println("Task 3 - Current Time :: " + LocalDateTime.now());
        };

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(runnableTask);
        executor.shutdown();

    }

}


//got introduced in JDK 5
