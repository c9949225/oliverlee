package net.oliver.olframework.util.reflection.mirror.gsp;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ConfigHolder implements IHolder {

	private Map<String,IHolder> cache = new LinkedHashMap<String,IHolder>();
	private String name;
	private Object obj;
	private String methodname;
	private IHolder parent;
	
	public IHolder getParent() {
		return parent;
	}

	public void setParent(IHolder parent) {
		this.parent = parent;
	}

	public ConfigHolder(String name) {
		super();
		this.name = name;
	}

	public IHolder get(String key) {
		return cache.get(key);
	}

	public String getName() {
		return name;
	}

	public Object getObj() {
		return obj;
	}

	public Map<String, IHolder> getCache() {
		return cache;
	}

	public void setCache(Map<String, IHolder> cache) {
		this.cache = cache;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public void set(String key, IHolder hodler) {
		cache.put(key, hodler);
	}
	
	@Override
	public String toString()
	{
		StringBuffer sb= new StringBuffer();
		sb.append("±¾½Úµã:"+name);
		Iterator iter = cache.entrySet().iterator();
		while(iter.hasNext())
		{
			Entry entry = (Entry)iter.next();
			sb.append("\r\n|----"+((IHolder)entry.getValue()).toString());
		}
		return sb.toString();
	}

	public String getMethodname() {
		return methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}
}
