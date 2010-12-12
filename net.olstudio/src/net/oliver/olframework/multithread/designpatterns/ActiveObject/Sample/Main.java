package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample;

import net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject.ActiveObject;
import net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject.ActiveObjectFactory;


public class Main {
    public static void main(String[] args) {
    	
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        
        // ��ͬ�̶߳�ʹ��ͬһ��ActiveObject,ʵ���ڴ湲��
        new MakerClientThread("Alice", activeObject).start();
        new MakerClientThread("Bobby", activeObject).start();
        new DisplayClientThread("Chris", activeObject).start();
    }
}
