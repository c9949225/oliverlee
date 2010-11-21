package net.oliver.olframework.designpatterns.l2lbridge;

/**
 *  The ConcreteImplementor
 */
public class TextImpMac implements TextImp {
    public TextImpMac() {
    }
    public void DrawTextImp() {
        System.out.println("The text has a Mac style !");
    }
}