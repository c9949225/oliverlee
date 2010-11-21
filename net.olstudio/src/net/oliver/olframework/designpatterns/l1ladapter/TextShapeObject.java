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
    	// Text �� TextSapeObject ����
        Text myText = new Text();
        // ����˼��: �������εĶ��������κ�Ķ���,���κ�Ķ�����ӱ����ζ���ķ��� eg: SetContent
        TextShapeObject myTextShapeObject = new TextShapeObject(myText);
        // �������;߱���Shape�ӿڵĹ���
        myTextShapeObject.Draw();
        myTextShapeObject.Border();
        // Text ��Ȼ�����Լ���Text����
        myTextShapeObject.SetContent("A test text !");
        System.out.println("The content in Text Shape is :" + myTextShapeObject.GetContent());
        
    }
}