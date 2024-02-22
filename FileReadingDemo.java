import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileReadingDemo {

    public static void main(String[] args) {

        String[] files = {"file1.txt", "file2.txt", "file3.txt"};

        // Create an executor service with fixed thread pool size
        ExecutorService executor = Executors.newFixedThreadPool(files.length);

        // Submit tasks for reading files
        for (String file : files) {
            executor.execute(new FileReaderTask(file));
        }

        executor.shutdown();
    }
}

class FileReaderTask implements Runnable {
    private String filename;

    public FileReaderTask(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[" + threadName + "] " + filename + ": " + line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + filename + ": " + e.getMessage());
        }
    }
}
