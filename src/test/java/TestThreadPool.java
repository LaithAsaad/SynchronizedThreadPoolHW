import java.util.ArrayList;
import java.util.List;

public class TestThreadPool {

    public static void main(String[] args) {
        // Create Integers that represent number of tasks, max number of tasks and real number of tasks
        int noOfthreads = 3;
        int maxNoOfTasks = 10;
        int numOfTasks = 10;
        // Create a ThreadPool with 10 threads and a capacity of 10 tasks
        ThreadPool threadPool = new ThreadPool(noOfthreads, maxNoOfTasks);

        List<Runnable> tasks = new ArrayList<>();
        // Create some tasks
        for (int i = 0; i < numOfTasks; i++) {
            final int taskNumber = i;
            Runnable task = () -> System.out.println("Task " + taskNumber + " executed by "+Thread.currentThread().getName());
            tasks.add(task);
        }

        // Add the tasks to the ThreadPool
        try {
            for (Runnable task:tasks) {
                threadPool.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Wait until all tasks are finished
        threadPool.waitUntilAllTasksFinished();

        // Stop the ThreadPool
        threadPool.stop();
    }
}
