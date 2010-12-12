package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject;

//scheduler.invoke�ǰ� ���ֲ�ͬ��Request�Ž�queueȥ
/**
 * ������ActiveObject����:
 * 		1. �����ַ���
 * 		2. ��ʾ�ַ���
 * �Ĺ���
 */
class Proxy implements ActiveObject {
	
    private final SchedulerThread scheduler; // ͨ����������,�Լ���������߳�,�ͽ���ActiveObject
    
    private final Servant servant; // ͨ������ִ����ײ�Ĺ���
    
    public Proxy(SchedulerThread scheduler, Servant servant) {
        this.scheduler = scheduler;
        this.servant = servant;
    }
    
    // ������������
    public Result makeString(int count, char fillchar) {
        FutureResult future = new FutureResult();
        // ��ͬ�ĵ��ã��ɴ��벻ͬ��servant��ʵ�ֲ�ͬ��Ч��
        scheduler.invoke(new MakeStringRequest(servant, future, count, fillchar));
        return future;
    }
    
    // ������������
    public void displayString(String string) {
        scheduler.invoke(new DisplayStringRequest(servant, string));
    }
}
