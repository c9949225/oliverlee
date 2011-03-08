package net.oliver.olframework.designpatterns.l2lbridge;
/**
 *  The Abstract of Text 
 */
public abstract class Text  {
    public abstract void DrawText(String text);
    // 通过这里传入不同的类型使得Text对象的实现者有不同的行为
    protected TextImp GetTextImp(String type) {
        if(type.equals("Mac")) {
            return new TextImpMac();
        } else if(type.equals("Linux")) {
            return new TextImpLinux();
        } else {
            return new TextImpMac();
        }
    }
}