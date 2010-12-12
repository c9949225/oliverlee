package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject;

class FutureResult extends Result {
    private Result result;
    private boolean ready = false;
    
    public synchronized void setResult(Result result) {
        this.result = result;
        this.ready = true;
        // ��ֵ֮��֪֮ͨǰ�ȴ����̣߳�������ȡֵ��
        notifyAll();
    }
    
    
    public synchronized Object getResultValue() {
        while (!ready) {
            try {
            	// ��δ��ֵ֮ǰ��������������̶߳��ȴ�
                wait();
            } catch (InterruptedException e) {
            }
        }
        // �ȴ��󣬻����������ȡֵ
        return result.getResultValue();
    }
}
