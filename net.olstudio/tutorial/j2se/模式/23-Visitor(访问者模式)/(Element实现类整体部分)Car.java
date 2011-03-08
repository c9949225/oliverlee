public class Car implements Element
{
    private Engine engine = new Engine();

    private Body body = new Body();

    private Wheel[] wheels = { new Wheel("Font left"), new Wheel("Front right"), new Wheel("Back left"),
            new Wheel("Back right") };

    public void accept(Visitor v)
    {
        // һ��Ԫ�ض������������
        v.visit(this);
        // ���岿�ֻ�Ҫ���ø����Ӳ�����visit����
        engine.accept(v);
        body.accept(v);
        for (int i = 0; i < 4; i++)
            wheels[i].accept(v);
    }
}