import java.util.concurrent.ThreadFactory;

public class PoolThreadRunnable implements Runnable {
    // PoolThreadRunnable class represent the task will execute
    private Thread thread = null;
    private SynchronizedTaskQueue taskQueue = null;
    private boolean isStopped = false;

    public PoolThreadRunnable(SynchronizedTaskQueue queue){
        taskQueue = queue;
    }

    public void run(){
        this.thread = Thread.currentThread();
        while(!isStopped()){
            try{
                Runnable runnable = (Runnable) taskQueue.removeTask();
                runnable.run();
            } catch(Exception e){
                //log or otherwise report exception,
                //but keep pool thread alive.
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        //break pool thread out of dequeue() call.
        if (this.thread != null) {
            this.thread.interrupt();
        }
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}
