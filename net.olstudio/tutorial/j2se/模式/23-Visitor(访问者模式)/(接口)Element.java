public interface Element
{
    // 每个元素必须接受一个访问者
    public void accept(Visitor v);
}