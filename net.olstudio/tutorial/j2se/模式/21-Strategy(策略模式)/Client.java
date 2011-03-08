public class Client
{
    public static void main(String[] args)
    {
        String[] data = new String[]
        { "Jack", "Maya", "Mikes", "Shadow" };
        // 同样都是TableExporter 但选择不同的实现者可以得到不同的效果
        TableExporter tb;
        tb = new HtmlExporter();
        System.out.println(tb.getExported(data));
        tb = new LineExporter();
        System.out.println(tb.getExported(data));
    }
}