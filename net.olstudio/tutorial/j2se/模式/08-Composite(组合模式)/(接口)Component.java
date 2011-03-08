public interface Component {
	
	public int getSize();

	public int getChildNum();

	public String getType();

	public String getName();

	public void add(Component c);

	public void remove(Component c);
}