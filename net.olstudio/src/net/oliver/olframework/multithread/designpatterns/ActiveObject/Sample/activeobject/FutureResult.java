package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject;

class FutureResult extends Result {
    private Result result;
    private boolean ready = false;
    
    public synchronized void setResult(Result result) {
        this.result = result;
        this.ready = true;
        // 设值之后通知之前等待的线程，可以来取值了
        notifyAll();
    }
    
    
    public synchronized Object getResultValue() {
        while (!ready) {
            try {
            	// 在未设值之前，让所有请求的线程都等待
                wait();
            } catch (InterruptedException e) {
            }
        }
        // 等待后，回来这里进行取值
        return result.getResultValue();
    }
}
