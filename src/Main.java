import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int count=0;
        for(int i=1; i<=1000000;i++)
            count++;
        return count;
    }

    public static void main(String[] args)
    {
        int num=100000000;
        int numThreads=100;
        int numbersPThread= num/numThreads;
        ExecutorService executorService= Executors.newFixedThreadPool(100);
        List<Future<Integer>> futures=new ArrayList<>();

        for(int i=0;i<100;i++)
        {
           
            Callable<Integer> task= new Main();
            Future<Integer> future= executorService.submit(task);
            futures.add(future);
        }

        int total=0;
        for(Future<Integer>future: futures)
        {
            try{
                total+=future.get();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        System.out.println("Total count: "+total);
    }
}