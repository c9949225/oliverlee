public class Body implements Element{
    public void accept(Visitor v){
       v.visit(this);
    }
}