import java.time.LocalDateTime;
import java.util.concurrent.*;

public class ExecutorDemo {

    private static final int noOfThreads = 30;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Runnable runnableTask = () -> {

           // System.out.println("Performing a runnable task...");

//            try {
//                TimeUnit.MILLISECONDS.sleep(1000);
//                System.out.println("Current Time :: "+ LocalDateTime.now());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

     //   };

        Callable callableTask = () ->{
            System.out.println("Performing Callable Task...");
            return "Callable task Performed";
        };

       // ExecutorService executor = Executors.newFixedThreadPool(noOfThreads);
        ExecutorService executor=Executors.newSingleThreadExecutor();
      //  Future status =executor.submit(runnableTask);

        Future status =executor.submit(callableTask);

        System.out.println(status.get());

        String[] domains={
                "https://google.com",
                "https://bing.com",
                "https://yahoo.com",
                "https://paypal.com",
                "https://wordpress.in",
                "https://www.ebay.com",
                "https://www.wikipedia.com"

        };

//        for(int i=0;i<domains.length;i++)
//        {
//            String url=domains[i];
//            PingUrl worker=new PingUrl(url);
//            executor.execute(worker);
//        }

       // executor.execute(runnableTask);
        executor.shutdown();

        if(executor.isTerminated()){
            System.out.println("Finished Executing :: ");
        }

    }

}


//got introduced in JDK 5
