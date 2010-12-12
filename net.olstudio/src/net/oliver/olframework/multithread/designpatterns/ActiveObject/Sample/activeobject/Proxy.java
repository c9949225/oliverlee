package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject;

//scheduler.invoke是把 各种不同的Request放进queue去
/**
 * 本类是ActiveObject具有:
 * 		1. 创建字符串
 * 		2. 显示字符串
 * 的功能
 */
class Proxy implements ActiveObject {
	
    private final SchedulerThread scheduler; // 通过它来调度,自己本身具有线程,就叫做ActiveObject
    
    private final Servant servant; // 通过它来执行最底层的工作
    
    public Proxy(SchedulerThread scheduler, Servant servant) {
        this.scheduler = scheduler;
        this.servant = servant;
    }
    
    // 放入制造请求
    public Result makeString(int count, char fillchar) {
        FutureResult future = new FutureResult();
        // 不同的调用，可传入不同的servant，实现不同的效果
        scheduler.invoke(new MakeStringRequest(servant, future, count, fillchar));
        return future;
    }
    
    // 放入消费请求
    public void displayString(String string) {
        scheduler.invoke(new DisplayStringRequest(servant, string));
    }
}
