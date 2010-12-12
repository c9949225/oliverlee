package net.oliver.olframework.multithread.designpatterns.ActiveObject.Sample.activeobject;
// 请求的通用父类
abstract class MethodRequest {
    protected final Servant servant;
    protected final FutureResult future;
    protected MethodRequest(Servant servant, FutureResult future) {
        this.servant = servant;
        this.future = future;
    }
    public abstract void execute();
}
