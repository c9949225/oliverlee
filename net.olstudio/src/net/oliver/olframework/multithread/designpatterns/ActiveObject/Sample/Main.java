package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample;

import net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject.ActiveObject;
import net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject.ActiveObjectFactory;


public class Main {
    public static void main(String[] args) {
    	
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        
        // 不同线程都使用同一个ActiveObject,实现内存共享
        new MakerClientThread("Alice", activeObject).start();
        new MakerClientThread("Bobby", activeObject).start();
        new DisplayClientThread("Chris", activeObject).start();
    }
}
