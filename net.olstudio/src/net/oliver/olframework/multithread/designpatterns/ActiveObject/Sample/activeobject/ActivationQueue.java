package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject;

// 用来维护整个生产消费过程
class ActivationQueue {
	
    private static final int MAX_METHOD_REQUEST = 100;
    private final MethodRequest[] requestQueue;
    
    private int tail;  // 下一個putRequest的地方
    private int head;  // 下一個takeRequest的地方
    private int count; // MethodRequest的數量

    public ActivationQueue() {
        this.requestQueue = new MethodRequest[MAX_METHOD_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }
    
    
    public synchronized void putRequest(MethodRequest request) {
        while (count >= requestQueue.length) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        requestQueue[tail] = request;
        tail = (tail + 1) % requestQueue.length;
        count++;
        // 在尾部加入一个Request,并通知等待的线程可以取了
        notifyAll();
    }
    
    
    public synchronized MethodRequest takeRequest() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        // 从头部取出一个Request,并通知等待线程可以放入了
        MethodRequest request = requestQueue[head];
        head = (head + 1) % requestQueue.length;
        count--;
        notifyAll();
        return request;
    }
}
