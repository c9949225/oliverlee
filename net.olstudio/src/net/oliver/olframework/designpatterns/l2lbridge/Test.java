package net.oliver.olframework.designpatterns.l2lbridge;


/**
 *  ��ϣ���ڳ��������ʵ�ֲ���֮����һ���̶��İ󶨹�ϵ
 * 
 *  �ص�����һ�����󣬳��ж�һ���ӿڵ�ʵ��,����һ�����͵�ʵ��ʱ�����;��и����͵�ʵ�ַ�ʽ�������������һ�����͵�ʵ��ʱ���;�������һ����Ϊ
 *  
 *  bridgeģʽʹ����ԶԲ�ͬ�ĳ���ӿں�ʵ�ֲ��ֽ�����ϣ��������ǽ�������
 *  
 *  A test client
 */
public class Test  {
    public Test() {
    }

    public static void main(String[] args) {
    	// TextBold������Mac���͵�ʵ��ʱ�;���Mac��Ϊ
        Text myText = new TextBold("Mac");
        myText.DrawText("=== A test String ===");
        // TextBold������Linux���͵�ʵ��ʱ�;���Linux��Ϊ
        myText =  new TextBold("Linux");
        myText.DrawText("=== A test String ===");

        System.out.println("------------------------------------------");
        
        // TextItalic������Mac���͵�ʵ��ʱ�;���Mac��Ϊ
        myText =  new TextItalic("Mac");
        myText.DrawText("=== A test String ===");

        // TextItalic������Linux���͵�ʵ��ʱ�;���Linux��Ϊ
        myText =  new TextItalic("Linux");
        myText.DrawText("=== A test String ===");        
    }
}