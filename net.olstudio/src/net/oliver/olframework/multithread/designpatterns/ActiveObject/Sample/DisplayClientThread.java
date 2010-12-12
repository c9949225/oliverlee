package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample;

import net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject.ActiveObject;

public class DisplayClientThread extends Thread {
	
	// 永远持有该对象
    private final ActiveObject activeObject;
    
    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }
    
    public void run() {
        try {
        	// 永远去做
            for (int i = 0; true; i++) {
                // 没有传回值的呼叫
                String string = Thread.currentThread().getName() + " " + i;
                // 调用后立即返回,相当于对ActiveObject送出了显示字符串的
                activeObject.displayString(string);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
        }
    }
}
