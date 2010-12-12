package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject;

class SchedulerThread extends Thread {
	
    private final ActivationQueue queue;
    
    public SchedulerThread(ActivationQueue queue) {
        this.queue = queue;
    }
    
    // 负责填充请求
    public void invoke(MethodRequest request) {
        queue.putRequest(request);
    }
    
    public void run() {

    	while (true) {
    		// 永远地去Queue里取请求,执行请求
    		MethodRequest request = queue.takeRequest();
            request.execute();
        }
    }
}
