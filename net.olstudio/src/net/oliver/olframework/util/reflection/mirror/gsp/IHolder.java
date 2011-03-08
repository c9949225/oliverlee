package net.oliver.olframework.util.reflection.mirror.gsp;

import java.util.Map;

public interface IHolder {
	
	public IHolder get(String key);
	public String getName();
	public Object getObj();
	public void setObj(Object obj);
	public void set(String key,IHolder hodler);
	public void setMethodname(String methodname);
	public String getMethodname();
	public Map<String, IHolder> getCache();
	public void setParent(IHolder parent);
	public IHolder getParent();
}
