public class Client
{
    public static void main(String[] args)
    {
        String[] data = new String[]
        { "Jack", "Maya", "Mikes", "Shadow" };
        // ͬ������TableExporter ��ѡ��ͬ��ʵ���߿��Եõ���ͬ��Ч��
        TableExporter tb;
        tb = new HtmlExporter();
        System.out.println(tb.getExported(data));
        tb = new LineExporter();
        System.out.println(tb.getExported(data));
    }
}