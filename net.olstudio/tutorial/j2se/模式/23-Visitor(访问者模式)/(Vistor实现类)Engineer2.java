public class Engineer2 implements Visitor
{
    protected String name;

    public Engineer(String name)
    {
        this.name = name;
    }

    public void visit(Wheel wheel)
    {
        System.out.println("Engineer2,Check " + wheel.getName() + "!");
    }

    public void visit(Engine engine)
    {
        System.out.println("Engineer2,Check Engine!");
    }

    public void visit(Body body)
    {
        System.out.println("Engineer2,Check Body!");
    }

    public void visit(Car car)
    {
        System.out.println(name + "Engineer2, Check Car!");
    }
}
