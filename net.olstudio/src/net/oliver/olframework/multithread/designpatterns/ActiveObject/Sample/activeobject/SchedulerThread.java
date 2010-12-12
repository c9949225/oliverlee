package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject;

class SchedulerThread extends Thread {
	
    private final ActivationQueue queue;
    
    public SchedulerThread(ActivationQueue queue) {
        this.queue = queue;
    }
    
    // �����������
    public void invoke(MethodRequest request) {
        queue.putRequest(request);
    }
    
    public void run() {

    	while (true) {
    		// ��Զ��ȥQueue��ȡ����,ִ������
    		MethodRequest request = queue.takeRequest();
            request.execute();
        }
    }
}
