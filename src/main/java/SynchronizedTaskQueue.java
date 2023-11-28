//import java.util.LinkedList;
//import java.util.Queue;
//
//public class SynchronizedTaskQueue {
//
//    private Queue<Object> taskQueue;
//
//    public SynchronizedTaskQueue() {
//        this.taskQueue = new LinkedList<>();
//    }
//
//    public synchronized void addTask(Object task) {
//        taskQueue.add(task);
//    }
//
//    public synchronized Object removeTask() {
//        return taskQueue.remove();
//    }
//
//    public synchronized Object peekTask() {
//        return taskQueue.peek();
//    }
//
//    // Add other methods as needed, and synchronize them as well
//}


import java.util.ArrayDeque;
import java.util.Deque;

public class SynchronizedTaskQueue {
    // SynchronizedTaskQueue class represent BlockingQueue but Synchronization was made manually using   non-concurrent data structures
    private Deque<Object> taskQueue;
    private int capacity;

    public SynchronizedTaskQueue() {
        this.taskQueue = new ArrayDeque<>();
        this.capacity = Integer.MAX_VALUE; // Default capacity
    }

    public SynchronizedTaskQueue(int capacity) {
        this.taskQueue = new ArrayDeque<>();
        this.capacity = capacity;
    }

    public synchronized void addTask(Object task) {
        if (taskQueue.size() >= capacity) {
            throw new IllegalStateException("Queue is full");
        }
        taskQueue.add(task);
    }

    public synchronized Object removeTask() {
        return taskQueue.remove();
    }

    public synchronized Object peekTask() {
        return taskQueue.peek();
    }
}

