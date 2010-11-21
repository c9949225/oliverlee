package net.oliver.olframework.designpatterns.l1ladapter;

/**
 *  The Object Adapter in this sample 
 */
public class TextShapeObject  implements Shape {
    private Text txt;
    public TextShapeObject(Text t) {
        txt = t;
    }
    public void Draw() {
        System.out.println("Draw a shap ! Impelement Shape interface !");
    }
    public void Border() {
        System.out.println("Set the border of the shap ! Impelement Shape interface !");
    }
    
    public void SetContent(String str) {
        txt.SetContent(str);
    }
    public String GetContent() {
        return txt.GetContent();
    }

    public static void main(String[] args) {
    	// Text 被 TextSapeObject 修饰
        Text myText = new Text();
        // 核心思想: 将被修饰的对象传入修饰后的对象,修饰后的对象添加被修饰对象的方法 eg: SetContent
        TextShapeObject myTextShapeObject = new TextShapeObject(myText);
        // 这样他就具备了Shape接口的功能
        myTextShapeObject.Draw();
        myTextShapeObject.Border();
        // Text 仍然具有自己的Text功能
        myTextShapeObject.SetContent("A test text !");
        System.out.println("The content in Text Shape is :" + myTextShapeObject.GetContent());
        
    }
}