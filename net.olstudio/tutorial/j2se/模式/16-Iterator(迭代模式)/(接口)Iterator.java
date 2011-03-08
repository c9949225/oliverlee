public interface Iterator {
	public Item first();

	public Item next();

	public boolean isDone();

	public Item currentItem();
}