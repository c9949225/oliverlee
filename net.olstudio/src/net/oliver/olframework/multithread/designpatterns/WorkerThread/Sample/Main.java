package net.oliver.olframework.multithread.designpatterns.WorkerThread.Sample;

public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel(5);   //工人消费者线程的數量
        channel.startWorkers();// 启动消费者
        
        // 启动生产者
        new ClientThread("Alice", channel).start();
        new ClientThread("Bobby", channel).start();
        new ClientThread("Chris", channel).start();
    }
}
