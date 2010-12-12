package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject;

public class ActiveObjectFactory {
	
	// ActiveObject����Ĵ����Ƚϸ��ӣ�����ʹ��Factoryģʽ
    public static ActiveObject createActiveObject() {
    	
        Servant servant = new Servant();// ����ʵ�ʸɻ�  0. �����ַ���        1. ��ӡ�ַ���
        ActivationQueue queue = new ActivationQueue(); // ����ά��������ˮ��
        SchedulerThread scheduler = new SchedulerThread(queue); // �����������,ͬʱ��ͣ��ȥ��ȡ����
        Proxy proxy = new Proxy(scheduler, servant); // ActiveObject ���е����߳�
        scheduler.start(); // ���������߳�,��ͣ��ȥȡRequest��ִ��Request
        return proxy;
    }
}
