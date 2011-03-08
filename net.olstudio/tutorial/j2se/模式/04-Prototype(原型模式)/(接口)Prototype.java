// 同时也要实现Cloneable接口
public interface Prototype extends Cloneable {
	public Object clone();

	public String getName();
}