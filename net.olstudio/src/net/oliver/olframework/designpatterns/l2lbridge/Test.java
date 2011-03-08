package net.oliver.olframework.designpatterns.l2lbridge;


/**
 *  不希望在抽象和他的实现部分之间有一个固定的绑定关系
 * 
 *  重点在于一个对象，持有对一个接口的实现,传入一个类型的实现时，他就具有该类型的实现方式，如果传入另外一个类型的实现时他就具有另外一种行为
 *  
 *  bridge模式使你可以对不同的抽象接口和实现部分进行组合，并对他们进行扩充
 *  
 *  A test client
 */
public class Test  {
    public Test() {
    }

    public static void main(String[] args) {
    	// TextBold被传入Mac类型的实现时就具有Mac行为
        Text myText = new TextBold("Mac");
        myText.DrawText("=== A test String ===");
        // TextBold被传入Linux类型的实现时就具有Linux行为
        myText =  new TextBold("Linux");
        myText.DrawText("=== A test String ===");

        System.out.println("------------------------------------------");
        
        // TextItalic被传入Mac类型的实现时就具有Mac行为
        myText =  new TextItalic("Mac");
        myText.DrawText("=== A test String ===");

        // TextItalic被传入Linux类型的实现时就具有Linux行为
        myText =  new TextItalic("Linux");
        myText.DrawText("=== A test String ===");        
    }
}