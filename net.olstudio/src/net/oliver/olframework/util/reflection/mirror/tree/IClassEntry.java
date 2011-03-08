package net.oliver.olframework.util.reflection.mirror.tree;

public interface IClassEntry {

	public String getEntryClassName();
	public Object getEntryClass();
	
	public IClassEntry getParent();
	public IClassEntry[] getChildren();
}
