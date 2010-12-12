package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject;

public class ActiveObjectFactory {
	
	// ActiveObject对象的创建比较复杂，所以使用Factory模式
    public static ActiveObject createActiveObject() {
    	
        Servant servant = new Servant();// 负责实际干活  0. 创建字符串        1. 打印字符串
        ActivationQueue queue = new ActivationQueue(); // 负责维护整个流水线
        SchedulerThread scheduler = new SchedulerThread(queue); // 负责填充请求,同时不停地去提取请求
        Proxy proxy = new Proxy(scheduler, servant); // ActiveObject 持有调度线程
        scheduler.start(); // 启动调度线程,不停地去取Request并执行Request
        return proxy;
    }
}
