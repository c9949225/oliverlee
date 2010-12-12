package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample;

import net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject.ActiveObject;

public class DisplayClientThread extends Thread {
	
	// ��Զ���иö���
    private final ActiveObject activeObject;
    
    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }
    
    public void run() {
        try {
        	// ��Զȥ��
            for (int i = 0; true; i++) {
                // û�д���ֵ�ĺ���
                String string = Thread.currentThread().getName() + " " + i;
                // ���ú���������,�൱�ڶ�ActiveObject�ͳ�����ʾ�ַ�����
                activeObject.displayString(string);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
        }
    }
}
