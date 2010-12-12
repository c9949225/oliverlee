package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject;

// ����ά�������������ѹ���
class ActivationQueue {
	
    private static final int MAX_METHOD_REQUEST = 100;
    private final MethodRequest[] requestQueue;
    
    private int tail;  // ��һ��putRequest�ĵط�
    private int head;  // ��һ��takeRequest�ĵط�
    private int count; // MethodRequest�Ĕ���

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
        // ��β������һ��Request,��֪ͨ�ȴ����߳̿���ȡ��
        notifyAll();
    }
    
    
    public synchronized MethodRequest takeRequest() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        // ��ͷ��ȡ��һ��Request,��֪ͨ�ȴ��߳̿��Է�����
        MethodRequest request = requestQueue[head];
        head = (head + 1) % requestQueue.length;
        count--;
        notifyAll();
        return request;
    }
}