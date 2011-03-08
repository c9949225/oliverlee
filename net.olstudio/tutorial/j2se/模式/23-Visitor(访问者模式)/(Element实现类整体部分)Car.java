public class Car implements Element
{
    private Engine engine = new Engine();

    private Body body = new Body();

    private Wheel[] wheels = { new Wheel("Font left"), new Wheel("Front right"), new Wheel("Back left"),
            new Wheel("Back right") };

    public void accept(Visitor v)
    {
        // 一般元素都会有这个方法
        v.visit(this);
        // 整体部分还要调用各个子部件的visit方法
        engine.accept(v);
        body.accept(v);
        for (int i = 0; i < 4; i++)
            wheels[i].accept(v);
    }
}