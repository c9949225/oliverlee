package net.oliver.olframework.designpatterns.l2lbridge;
/**
 *  The Abstract of Text 
 */
public abstract class Text  {
    public abstract void DrawText(String text);
    // ͨ�����ﴫ�벻ͬ������ʹ��Text�����ʵ�����в�ͬ����Ϊ
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